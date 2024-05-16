package com.authenticationservice.authenticationservice.security;

import com.authenticationservice.authenticationservice.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}


