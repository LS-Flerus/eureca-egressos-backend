package com.eureca.egressos.service.auth;

import org.springframework.security.core.Authentication;

public interface AuthService {
    String authenticate(Authentication authentication);
}
