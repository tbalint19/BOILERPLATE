package com.base.defaultAdmin.controller.appUserAuth;

import com.base.defaultAdmin.http.response.SuccessResponse;
import com.base.defaultAdmin.model.appUserAuth.appUser.AppUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class AppUserController extends AbstractAppUserAPI {

    @GetMapping("/all")
    public List<AppUser> getAllAppUser() {
        return appUserRepository.findAll();
    }

    @PostMapping("/deactivate")
    public SuccessResponse deactivate(AppUser appUser) {
        appUser.setActive(false);
        appUserRepository.save(appUser);
        return new SuccessResponse(true);
    }

    @PostMapping("/activate")
    public SuccessResponse activate(AppUser appUser) {
        appUser.setActive(true);
        appUserRepository.save(appUser);
        return new SuccessResponse(true);
    }
}
