package com.ewmw.addr.http.controllers.auth;

import com.ewmw.addr.config.JwtUserDetailsService;
import com.ewmw.addr.exceptions.responses.BadCredentialsResponse;
import com.ewmw.addr.http.requests.AuthenticateUserRequest;
import com.ewmw.addr.http.responses.auth.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    protected JwtHelper jwtHelper;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    protected JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody @Valid AuthenticateUserRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(authenticate.getName());
            String jwt = jwtHelper.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new BadCredentialsResponse());
        }
    }
}
