package com.seis602.possystem.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.seis602.possystem.model.CustomUserDetails;
import com.seis602.possystem.model.Shift;
import com.seis602.possystem.model.User;
import com.seis602.possystem.service.ShiftService;
import com.seis602.possystem.service.UserService;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    
	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private UserService userService;
	
	
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
    {
        CustomUserDetails principal = (CustomUserDetails) event.getAuthentication().getPrincipal();
        System.out.println("Session created for: " + principal.getFirstName() + " " + principal.getLastName());
        
        // create user shift
        User user = userService.getUser(principal.getId());
        shiftService.saveShift(new Shift(user));
    }
}