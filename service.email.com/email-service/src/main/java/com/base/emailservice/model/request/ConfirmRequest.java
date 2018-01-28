package com.base.emailservice.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConfirmRequest {

    private String to;
    private String name;
    private String confirmationCode;
    private String link;

}
