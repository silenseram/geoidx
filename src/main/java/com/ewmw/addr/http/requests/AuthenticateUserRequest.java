package com.ewmw.addr.http.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateUserRequest {
    @Getter @Setter
    @NotBlank
    @Length(min = 5)
    @Email
    protected String email;

    @Getter @Setter
    @NotBlank
    @Length(min = 3)
    protected String password;
}
