package com.ewmw.addr.http.filters;

import com.ewmw.addr.config.JwtUserDetailsService;
import com.ewmw.addr.http.controllers.auth.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    protected JwtHelper jwtHelper;

    @Autowired
    protected JwtUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            authorize(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    protected void authorize(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (! headerHasValidFormat(authorization))
            return;

        String token = authorization.substring(7);

        String username = jwtHelper.extractUsername(token);

        if (username == null || SecurityContextHolder.getContext().getAuthentication() != null)
            return;

        var userDetails = userDetailsService.loadUserByUsername(username);

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
//        usernamePasswordAuthenticationToken.setDetails(userDetails);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    protected boolean headerHasValidFormat(String authHeader) {
        return authHeader != null && authHeader.startsWith("Bearer ");
    }
}
