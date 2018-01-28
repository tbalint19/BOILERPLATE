package com.base.userClient.model.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckResponse {

    private Boolean available;

    public CheckResponse(Boolean available) {
        this.available = available;
    }

}
