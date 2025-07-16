package com.taskManagement.taskManagement.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.taskManagement.taskManagement.token.filter.JWTRequestFilter;
import com.taskManagement.taskManagement.token.service.AdminUserLogin;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final JWTRequestFilter JWTFilter;

	private final AdminUserLogin adminUserLogin;

	public SecurityConfig(JWTRequestFilter jWTFilter, AdminUserLogin adminUserLogin) {
		super();
		JWTFilter = jWTFilter;
		this.adminUserLogin = adminUserLogin;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(adminUserLogin);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/admin/user", "/api/microservice","/api/microservice/**","/swagger-ui.html",
						"/swagger-ui/**", "/api/admin/user/login").permitAll().anyRequest().authenticated())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(exception -> exception.authenticationEntryPoint((request, response, e) -> {
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					response.setContentType("application/json");
					response.getWriter().write("{\"message\": \"You are not authenticated.\", \"status\": \"401\"}");
				}));

		// Add your JWT filter here
		http.addFilterBefore(JWTFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	

}
