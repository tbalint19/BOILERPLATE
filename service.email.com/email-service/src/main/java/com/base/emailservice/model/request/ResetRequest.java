package com.base.emailservice.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetRequest {

    private String to;
    private String name;
    private String link;

}
