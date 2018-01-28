package com.base.userClient.controller.auth;

import com.base.userClient.controller.microservice.EmailServiceController;
import com.base.userClient.model.userAuth.ApplicationUser;
import com.base.userClient.repository.userAuth.UserRepository;
import com.base.userClient.service.auth.CheckService;
import com.base.userClient.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public abstract class AuthAPI {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CheckService checkService;

    @Autowired
    EmailServiceController emailServiceController;

    ApplicationUser getUser(Principal principal){

        return userRepository.findByUsername(principal.getName());
    }

}
