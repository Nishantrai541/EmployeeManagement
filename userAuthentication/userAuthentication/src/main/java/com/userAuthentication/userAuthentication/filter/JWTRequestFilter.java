package com.userAuthentication.userAuthentication.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.userAuthentication.userAuthentication.service.admin.login.AdminUserLogin;
import com.userAuthentication.userAuthentication.utils.jwt.JWTUtils;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private AdminUserLogin loginService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHadder = request.getHeader("Authorization");
		String userName = null;
		String jwt = null;
		if (authorizationHadder != null && authorizationHadder.startsWith("Bearer ")) {
			try {

				jwt = authorizationHadder.substring(7);
				userName = jwtUtils.getUsernameFromToken(jwt);

				if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.loginService.loadUserByUsername(userName);
					if (jwtUtils.validateToken(jwt, userDetails)) {

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						// After setting the Authentication in the context, we specify
						// that the current user is authenticated. So it passes the
						// Spring Security Configurations successfully.
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}
			} catch (ExpiredJwtException exception) {
				throw new ExpiredJwtException(null, null, "Session Expired", exception);
			} catch (UsernameNotFoundException e) {
				throw new UsernameNotFoundException("Invalid User Details");
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		filterChain.doFilter(request, response);
	}

}
