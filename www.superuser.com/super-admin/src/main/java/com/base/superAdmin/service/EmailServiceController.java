package com.base.superAdmin.service;

import com.base.superAdmin.http.request.ResetEmailRequest;
import com.base.superAdmin.http.response.AttemptResponse;
import com.base.superAdmin.model.superAdminAuth.superAdmin.SuperAdmin;
import com.base.superAdmin.model.superAdminAuth.superAdminPasswordReset.SuperAdminPasswordReset;
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

    public AttemptResponse sendResetEmail(SuperAdminPasswordReset superAdminPasswordReset){
        SuperAdmin superAdmin = superAdminPasswordReset.getSuperAdmin();
        ResetEmailRequest request = new ResetEmailRequest();
        request.setName(superAdmin.getUsername());
        request.setTo(superAdmin.getEmail());
        String url = APP_URL + "/reset?code=" + superAdminPasswordReset.getCode() + "&user=" + superAdmin.getUsername();
        request.setLink(url);
        return post("/reset", request);
    }
}
