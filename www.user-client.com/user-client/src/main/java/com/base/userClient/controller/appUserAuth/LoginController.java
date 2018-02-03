package com.base.userClient.controller.appUserAuth;

import com.base.userClient.http.request.LoginRequest;
import com.base.userClient.http.response.TokenResponse;
import com.base.userClient.model.appUserAuth.appUser.AppUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.security.model.AuthEntity;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController extends AbstractAuthAPI {

    @PostMapping("/public")
    public TokenResponse login(@RequestBody LoginRequest request) {
        AppUser appUser = findByCredential(request.getCredential());

        if (appUser == null)
            return new TokenResponse(null);

        AuthEntity authenticatedUser = securityManager
                .authenticate(appUser, request.getPassword());

        if (authenticatedUser == null)
            return new TokenResponse(null);

        String token = securityManager.authorize(appUser);
        return new TokenResponse(token);
    }

}

