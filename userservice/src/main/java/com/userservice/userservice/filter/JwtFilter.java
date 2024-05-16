package com.userservice.userservice.filter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // Parse and validate the token and set the user id from claims in the request header as an attribute.

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.println("Token Missing");
        } else {
            String jwtToken = authHeader.substring(7);
            String userdetail = Jwts.parser().setSigningKey("MyKey").parseClaimsJws(jwtToken).getBody().getSubject();
            request.setAttribute("emailId", userdetail);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
