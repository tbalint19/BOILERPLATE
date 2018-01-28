package com.base.superAdmin.http.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    private String credential;
    private String password;
}
