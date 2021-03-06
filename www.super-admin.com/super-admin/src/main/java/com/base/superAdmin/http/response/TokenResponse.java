package com.base.superAdmin.http.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {

    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

}
