package com.base.userClient.service;

import com.base.userClient.http.request.ConfirmEmailRequest;
import com.base.userClient.http.request.ResetEmailRequest;
import com.base.userClient.http.response.AttemptResponse;
import com.base.userClient.model.appUserAuth.appUser.AppUser;
import com.base.userClient.model.appUserAuth.appUserPasswordReset.AppUserPasswordReset;
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

    public AttemptResponse sendConfirmationEmail(AppUser user){
        ConfirmEmailRequest request = new ConfirmEmailRequest();
        request.setTo(user.getEmail());
        request.setName(user.getUsername());
        String url = APP_URL + "/confirm?code=" + user.getAppUserConfirmation().getCode() + "&user=" + user.getUsername();
        request.setLink(url);
        request.setConfirmationCode(user.getAppUserConfirmation().getCode());
        return post("/confirmation", request);
    }

    public AttemptResponse sendResetEmail(AppUserPasswordReset appUserPasswordReset){
        AppUser appUser = appUserPasswordReset.getAppUser();
        ResetEmailRequest request = new ResetEmailRequest();
        request.setName(appUser.getUsername());
        request.setTo(appUser.getEmail());
        String url = APP_URL + "/reset?code=" + appUserPasswordReset.getCode() + "&user=" + appUser.getUsername();
        request.setLink(url);
        return post("/reset", request);
    }
}

