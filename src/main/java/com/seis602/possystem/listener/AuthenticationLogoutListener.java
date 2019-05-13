package com.seis602.possystem.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.seis602.possystem.model.CustomUserDetails;

@WebListener
public class AuthenticationLogoutListener implements HttpSessionListener {
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session Destroyed");
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    
	    if (authentication != null) {
	    	CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
	    	System.out.println(principal.getFirstName() + " " + principal.getLastName());
	    }
	    
	}

}