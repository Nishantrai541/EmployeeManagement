package com.taskManagement.taskManagement.feign.RequestInterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // Extract the JWT token from the SecurityContext
        String jwtToken = getJwtTokenFromSecurityContext();
        
        if (jwtToken != null) {
            // Add the Authorization header to the Feign request
            template.header("Authorization", "Bearer " + jwtToken);
        }
    }

    private String getJwtTokenFromSecurityContext() {

    	
    	
    	  ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
          
          if (attributes != null) {
              HttpServletRequest request = attributes.getRequest();
              
              // Extract the JWT token from the request header
              return getJwtFromAuthorizationHeader(request);
          }
          return null;
    }
    
    private String getJwtFromAuthorizationHeader(HttpServletRequest request) {
        // Get the Authorization header (which should contain the Bearer token)
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extract the token part after "Bearer "
            return authHeader.substring(7); // "Bearer " is 7 characters long
        }
        return null; // Return null if there's no token
    }
}
