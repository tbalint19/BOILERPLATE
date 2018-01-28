package com.base.defaultAdmin.controller.defaultAdminAuth;

import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdmin.DefaultAdminRepository;
import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdminPasswordReset.DefaultAdminPasswordResetRepostory;
import com.base.defaultAdmin.service.EmailServiceController;
import org.springframework.beans.factory.annotation.Autowired;
import util.security.SecurityManager;

public abstract class AbstractAuthAPI {

    @Autowired
    public DefaultAdminRepository defaultAdminRepository;

    @Autowired
    public DefaultAdminPasswordResetRepostory defaultAdminPasswordResetRepostory;

    @Autowired
    public EmailServiceController emailServiceController;

    @Autowired
    public SecurityManager securityManager;
}
