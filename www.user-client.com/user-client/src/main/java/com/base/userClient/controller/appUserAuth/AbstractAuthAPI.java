package com.base.userClient.controller.appUserAuth;

import com.base.userClient.model.appUserAuth.appUser.AppUser;
import com.base.userClient.model.appUserAuth.appUser.AppUserRepository;
import com.base.userClient.model.appUserAuth.appUserConfirmation.AppUserConfirmationRepository;
import com.base.userClient.model.appUserAuth.appUserPasswordReset.AppUserPasswordResetRepository;
import com.base.userClient.service.EmailServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import util.security.SecurityManager;

public class AbstractAuthAPI {

    @Autowired
    protected AppUserRepository appUserRepository;

    @Autowired
    protected AppUserConfirmationRepository appUserConfirmationRepository;

    @Autowired
    protected AppUserPasswordResetRepository appUserPasswordResetRepository;

    @Autowired
    protected SecurityManager securityManager;

    @Autowired
    protected EmailServiceController emailServiceController;

    protected AppUser findByCredential(String credential) {
        String cleansedCredential = securityManager.cleanse(credential);

        if (securityManager.isEmail(credential))
            return appUserRepository.findByEmail(cleansedCredential);
        else
            return appUserRepository.findByUsername(cleansedCredential);
    }
}
