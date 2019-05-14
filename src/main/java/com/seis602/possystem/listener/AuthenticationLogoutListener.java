package com.seis602.possystem.listener;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.seis602.possystem.model.CustomUserDetails;
import com.seis602.possystem.model.Shift;
import com.seis602.possystem.model.User;
import com.seis602.possystem.service.ShiftService;
import com.seis602.possystem.service.UserService;

@WebListener
public class AuthenticationLogoutListener implements HttpSessionListener {
	
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    
	    if (authentication != null) {
	    	CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
	    	System.out.println("Session destroyed for: " + principal.getFirstName() + " " + principal.getLastName());
	    	
	    	User user = userService.getUser(principal.getId());
	    	Shift shift = shiftService.getActiveShiftByUser(user);
	    	
	    	// set end of shift time
	    	shift.setEndTime(new Date());
	    	
	    	System.out.println(shift.getDuration());
	    	
	    	shiftService.saveShift(shift);
	    }
	    
	}

}