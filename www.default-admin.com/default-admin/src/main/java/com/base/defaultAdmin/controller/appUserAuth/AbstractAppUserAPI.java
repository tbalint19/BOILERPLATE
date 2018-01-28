package com.base.defaultAdmin.controller.appUserAuth;

import com.base.defaultAdmin.model.appUserAuth.appUser.AppUserRepository;
import com.base.defaultAdmin.model.appUserAuth.appUserConfirmation.AppUserConfirmationRepository;
import com.base.defaultAdmin.model.appUserAuth.appUserPasswordReset.AppUserPasswordResetRepository;
import com.base.defaultAdmin.service.EmailServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import util.security.SecurityManager;

public abstract class AbstractAppUserAPI {

    @Autowired
    public AppUserRepository appUserRepository;

    @Autowired
    public AppUserConfirmationRepository appUserConfirmationRepository;

    @Autowired
    public AppUserPasswordResetRepository appUserPasswordResetRepository;

    @Autowired
    public EmailServiceController emailServiceController;

    @Autowired
    public SecurityManager securityManager;
}
