package com.ewmw.addr.http.controllers.tokens;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    public Object generateToken() {
        return new Object();
    }

    public Object revokeToken() {
        return new Object();
    }

    public Object getToken() {
        return new Object();
    }


}
