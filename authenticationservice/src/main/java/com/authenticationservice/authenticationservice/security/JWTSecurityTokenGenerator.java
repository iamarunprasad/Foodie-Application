package com.authenticationservice.authenticationservice.security;

import com.authenticationservice.authenticationservice.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class JWTSecurityTokenGenerator implements SecurityTokenGenerator {

    @Override
    public Map<String,String> generateToken(User user) {
        String token=null;
        token= Jwts.builder().setSubject(user.getEmailId())
                .setIssuedAt(new Date()).
                signWith(SignatureAlgorithm.HS256,"MyKey").compact();
        Map<String,String> map=new HashMap<>();
        map.put("token",token);
        map.put("message","Login Successful");
        return map;
    }

}


