package com.ewmw.addr.http.responses.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AuthenticationResponse {

    @Getter @Setter
    protected String jwt;
}
