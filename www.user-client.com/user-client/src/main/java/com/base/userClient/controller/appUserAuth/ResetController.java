package com.base.userClient.controller.appUserAuth;

import com.base.userClient.http.request.ResetRequest;
import com.base.userClient.http.response.SuccessResponse;
import com.base.userClient.model.appUserAuth.appUser.AppUser;
import com.base.userClient.model.appUserAuth.appUserPasswordReset.AppUserPasswordReset;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/reset")
public class ResetController extends AbstractAuthAPI {

    @PostMapping("/start/public")
    public SuccessResponse startProcess(@RequestParam String credential) {
        AppUser appUser = findByCredential(credential);

        if (appUser == null)
            return new SuccessResponse(false);

        AppUserPasswordReset appUserPasswordReset = new AppUserPasswordReset(appUser);
        appUserPasswordReset.setCode(securityManager.getRandomString(25));
        appUserPasswordResetRepository.save(appUserPasswordReset);

        emailServiceController.sendResetEmail(appUserPasswordReset);

        return new SuccessResponse(true);
    }

    @PostMapping("/finish/public")
    public SuccessResponse finishProcess(@RequestBody ResetRequest request) {
        AppUser appUser = appUserRepository.findByUsername(request.getUsername());
        if (appUser == null)
            return new SuccessResponse(false);

        AppUserPasswordReset appUserPasswordReset = appUserPasswordResetRepository
                .findByCode(request.getCode());
        if (appUserPasswordReset == null)
            return new SuccessResponse(false);

        if (appUserPasswordReset.getAppUser().getId().equals(appUser.getId()))
            return new SuccessResponse(false);

        if (appUserPasswordReset.getUsed())
            return new SuccessResponse(false);

        if (!appUserPasswordReset.getCode().equals(request.getCode()) ||
                appUserPasswordReset.getUsed())
            return new SuccessResponse(false);

        appUser.setPassword(request.getPassword());
        securityManager.secure(appUser);
        appUserRepository.save(appUser);

        return new SuccessResponse(true);
    }
}
