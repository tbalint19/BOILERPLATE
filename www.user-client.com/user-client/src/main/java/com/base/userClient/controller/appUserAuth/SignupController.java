package com.base.userClient.controller.appUserAuth;

import com.base.userClient.http.request.SignupRequest;
import com.base.userClient.http.response.SuccessResponse;
import com.base.userClient.model.appUserAuth.appUser.AppUser;
import com.base.userClient.model.appUserAuth.appUserConfirmation.AppUserConfirmation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/signup")
public class SignupController extends AbstractAuthAPI {

    @PostMapping("/public")
    public SuccessResponse signup(@RequestBody SignupRequest request) {
        AppUser appUserWithUsername = appUserRepository
                .findByUsername(securityManager.cleanse(request.getUsername()));
        if (appUserWithUsername != null)
            return new SuccessResponse(false);

        AppUser appUserWithEmail = appUserRepository
                .findByEmail(securityManager.cleanse(request.getEmail()));
        if (appUserWithEmail != null)
            return new SuccessResponse(false);

        AppUser appUser = new AppUser(
                request.getUsername(), request.getEmail(), request.getPassword());
        securityManager.secure(appUser);

        AppUserConfirmation appUserConfirmation = new AppUserConfirmation();
        appUserConfirmation.setCode(securityManager.getRandomString(25));
        appUser.setAppUserConfirmation(appUserConfirmation);

        appUserRepository.save(appUser);

        emailServiceController.sendConfirmationEmail(appUser);

        return new SuccessResponse(true);
    }
}
