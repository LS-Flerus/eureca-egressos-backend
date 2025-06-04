package com.eureca.egressos.service.auth.impl;

import com.eureca.egressos.service.auth.AuthService;
import com.eureca.egressos.service.auth.JwtService;
import com.eureca.egressos.service.auth.AuthService;
import com.eureca.egressos.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtService jwtService;

    @Autowired
    public AuthServiceImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }

}