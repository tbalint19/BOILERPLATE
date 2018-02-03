package com.base.userClient.controller.appUserAuth;

import com.base.userClient.http.response.AvailabilityResponse;
import com.base.userClient.http.response.SuccessResponse;
import com.base.userClient.model.appUserAuth.appUser.AppUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/check")
public class CheckController extends AbstractAuthAPI {

    @GetMapping("/username/public")
    public AvailabilityResponse checkUsername(@RequestParam String username) {
        AppUser appuser = appUserRepository.findByUsername(
                securityManager.cleanse(username));
        return new AvailabilityResponse(appuser == null);
    }

    @GetMapping
    public AvailabilityResponse checkEmail(@RequestParam String email) {
        AppUser appUser = appUserRepository.findByEmail(
                securityManager.cleanse(email));
        return new AvailabilityResponse(appUser == null);
    }

    @GetMapping
    public SuccessResponse findForCredential(@RequestParam String credential) {
        AppUser appUser = findByCredential(credential);

        return new SuccessResponse(appUser != null);

    }
}
