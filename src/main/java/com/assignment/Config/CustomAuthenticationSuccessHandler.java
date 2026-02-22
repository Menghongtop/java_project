package com.assignment.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // Default fallback
        String redirectUrl = "/dashboard";

        // Check roles (case-sensitive – must match exactly what’s in DB / GrantedAuthority)
        boolean isRecruiter = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_RECRUITER"));

        boolean isJobSeeker = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_JOB_SEEKER"));

        if (isRecruiter) {
            redirectUrl = "/recruiter/dashboard";
        } else if (isJobSeeker) {
            redirectUrl = "/jobseeker/dashboard";
        }

        response.sendRedirect(redirectUrl);
    }
}