package com.base.defaultAdmin.service;

import com.base.defaultAdmin.http.request.ResetEmailRequest;
import com.base.defaultAdmin.http.response.AttemptResponse;
import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdmin.DefaultAdmin;
import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdminPasswordReset.DefaultAdminPasswordReset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import util.microservice.MicroServiceController;

@Service
public class EmailServiceController extends MicroServiceController {

    @Value("${service.email.url}")
    private String SERVICE_URL;

    private AttemptResponse post(String url, Object data){
        return restTemplate.postForObject(
                SERVICE_URL + "/api/send" + url, data , AttemptResponse.class);
    }

    public AttemptResponse sendResetEmail(DefaultAdminPasswordReset defaultAdminPasswordReset){
        DefaultAdmin defaultAdmin = defaultAdminPasswordReset.getDefaultAdmin();
        ResetEmailRequest request = new ResetEmailRequest();
        request.setName(defaultAdmin.getUsername());
        request.setTo(defaultAdmin.getEmail());
        String url = APP_URL + "/reset?code=" + defaultAdminPasswordReset.getCode() + "&user=" + defaultAdmin.getUsername();
        request.setLink(url);
        return post("/reset", request);
    }
}

