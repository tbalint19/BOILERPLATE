package com.base.userClient.http.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetEmailRequest {

    private String to;

    private String name;

    private String link;

}
