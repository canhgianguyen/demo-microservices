package com.example.orderservice.service;

import com.example.orderservice.model.dto.CustomPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private CustomPrincipal loggedUser;

    private Authentication authentication;

    public AuthService() {
        this.authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    public CustomPrincipal getLoggedUser() {
        if (isLoggedUser()) {
            loggedUser = (CustomPrincipal) authentication.getPrincipal();
        }
        return loggedUser;
    }

    private boolean isLoggedUser() {
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return true;
    }
}
