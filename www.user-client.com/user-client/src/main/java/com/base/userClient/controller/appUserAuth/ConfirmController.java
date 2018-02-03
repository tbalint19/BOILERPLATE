package com.base.userClient.controller.appUserAuth;

import com.base.userClient.http.request.ConfirmRequest;
import com.base.userClient.http.response.SuccessResponse;
import com.base.userClient.model.appUserAuth.appUser.AppUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/confirm")
public class ConfirmController extends AbstractAuthAPI {

    @GetMapping("/start/public")
    public SuccessResponse startProcess(@RequestParam String credential) {
        AppUser appUser = findByCredential(credential);

        if (appUser == null)
            return new SuccessResponse(false);

        emailServiceController.sendConfirmationEmail(appUser);

        return new SuccessResponse(true);
    }

    @PostMapping("/finish/public")
    public SuccessResponse finishProcess(@RequestBody ConfirmRequest request) {
        AppUser appUser = appUserRepository
                .findByUsername(securityManager.cleanse(request.getUsername()));
        if (appUser == null)
            return new SuccessResponse(false);

        if (!appUser.getAppUserConfirmation().getCode().equals(request.getCode()))
            return new SuccessResponse(false);

        appUser.setConfirmed(true);
        appUserRepository.save(appUser);

        return new SuccessResponse();
    }

}
