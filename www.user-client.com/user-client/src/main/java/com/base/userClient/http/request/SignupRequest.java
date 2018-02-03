package com.base.userClient.http.request;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;

    private String email;

    private String password;

    private String passwordAgain;
}
