package com.example.paymentservice.service;

import com.example.paymentservice.model.dto.CustomPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private CustomPrincipal loggedUser;

    public CustomPrincipal getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isLoggedUser()) {
            loggedUser = (CustomPrincipal) authentication.getPrincipal();
        }
        return loggedUser;
    }

    private boolean isLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return true;
    }
}
