package com.base.superAdmin.controller.defaultAdminAuth;

import util.security.SecurityManager;
import com.base.superAdmin.model.defaultAdminAuth.defaultAdmin.DefaultAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDefaultAdminAPI {

    @Autowired
    protected DefaultAdminRepository defaultAdminRepository;

    @Autowired
    protected SecurityManager securityManager;
}
