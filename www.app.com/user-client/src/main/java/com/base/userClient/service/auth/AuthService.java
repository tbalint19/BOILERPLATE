package com.base.userClient.service.auth;

import com.base.userClient.model.userAuth.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String hash(String rawPassword){
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public ApplicationUser authenticate(ApplicationUser user, String rawPassword){
        if (user == null){
            return null;
        }
        if (!checkPassword(rawPassword, user.getPassword())){
            return null;
        }
        return user;
    }

    private Boolean checkPassword(String rawPassword, String encodedPassword){
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }


}