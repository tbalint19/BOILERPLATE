package com.base.userClient.http.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfirmRequest {

    private String username;

    private String code;

}
