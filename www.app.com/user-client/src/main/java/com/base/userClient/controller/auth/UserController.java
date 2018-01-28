package com.base.userClient.controller.auth;


import com.base.userClient.model.userAuth.ApplicationUser;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController extends AuthAPI {

    @GetMapping("/me")
    public ApplicationUser getMe(Principal principal){
        ApplicationUser user = getUser(principal);
        return user;
    }

}
