package com.base.superAdmin.controller.superAdminAuth;

import util.security.SecurityManager;
import com.base.superAdmin.model.superAdminAuth.superAdmin.SuperAdminRepository;
import com.base.superAdmin.service.EmailServiceController;
import com.base.superAdmin.model.superAdminAuth.superAdminPasswordReset.SuperAdminPasswordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAuthAPI {

    @Autowired
    public SuperAdminRepository superAdminRepository;

    @Autowired
    public SuperAdminPasswordResetRepository superAdminPasswordResetRepository;

    @Autowired
    public EmailServiceController emailServiceController;

    @Autowired
    public SecurityManager securityManager;

}
