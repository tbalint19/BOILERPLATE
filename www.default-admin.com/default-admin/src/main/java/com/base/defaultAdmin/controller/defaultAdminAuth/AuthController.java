package com.base.defaultAdmin.controller.defaultAdminAuth;

import com.base.defaultAdmin.http.request.LoginRequest;
import com.base.defaultAdmin.http.response.TokenResponse;
import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdmin.DefaultAdmin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.security.model.AuthEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends AbstractAuthAPI {

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        DefaultAdmin defaultAdmin = defaultAdminRepository.findByUsername(request.getUsername());
        AuthEntity authenticatedDefaultAdmin = securityManager.authenticate(
                defaultAdmin, request.getPassword());

        if (authenticatedDefaultAdmin == null)
            return new TokenResponse(null);

        if (!authenticatedDefaultAdmin.getActive())
            return new TokenResponse(null);

        String token = securityManager.authorize(authenticatedDefaultAdmin);
        return new TokenResponse(token);
    }

}
