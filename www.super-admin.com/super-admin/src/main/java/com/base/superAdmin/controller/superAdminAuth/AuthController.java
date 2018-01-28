package com.base.superAdmin.controller.superAdminAuth;

import com.base.superAdmin.http.request.LoginRequest;
import com.base.superAdmin.http.response.TokenResponse;
import com.base.superAdmin.model.superAdminAuth.superAdmin.SuperAdmin;
import org.springframework.web.bind.annotation.*;
import util.security.model.AuthEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends AbstractAuthAPI {

    @PostMapping("/login")
    public TokenResponse loginUser(@RequestBody LoginRequest request){
        SuperAdmin superAdmin = superAdminRepository.findByUsername(request.getCredential());
        String token = null;
        AuthEntity authenticatedSuperAdmin = securityManager.authenticate(
                superAdmin, request.getPassword());
        if (authenticatedSuperAdmin != null) {
            token = securityManager.authorize(authenticatedSuperAdmin);
        }
        return new TokenResponse(token);
    }

}
