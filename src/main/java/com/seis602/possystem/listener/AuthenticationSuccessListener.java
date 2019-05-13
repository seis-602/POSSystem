package com.seis602.possystem.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.seis602.possystem.model.CustomUserDetails;

@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event)
    {
    	System.out.println("session Created");
        CustomUserDetails principal = (CustomUserDetails) event.getAuthentication().getPrincipal();
        System.out.println(principal.getFirstName() + " " + principal.getLastName());
    }
}