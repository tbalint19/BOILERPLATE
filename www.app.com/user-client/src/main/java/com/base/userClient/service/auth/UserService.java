package com.base.userClient.service.auth;

import com.base.userClient.model.userAuth.ApplicationUser;
import com.base.userClient.model.userAuth.Confirmation;
import com.base.userClient.repository.userAuth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckService checkService;

    public ApplicationUser getUserByCredential(String credential){
        ApplicationUser userInDb;
        if (credential.contains("@")){
            userInDb = userRepository.findByEmailIgnoreCase(
                    checkService.cleanseEmail(credential));
        } else {
            userInDb = userRepository.findByUsernameIgnoreCase(credential);
        }
        return userInDb;
    }

    public ApplicationUser createUser(ApplicationUser user, Confirmation confirmation){
        String hashedPassword = authService.hash(user.getPassword());
        user.setPassword(hashedPassword);
        user.setConfirmed(false);
        user.setConfirmation(confirmation);
        user.setCreated(new Date());
        String cleansedEmail = checkService.cleanseEmail(user.getEmail());
        user.setEmail(cleansedEmail);
        userRepository.save(user);
        return user;
    }

    public String loginUser(ApplicationUser user, String rawPassword){
        ApplicationUser authenticatedUser = authService.authenticate(user, rawPassword);
        String token = null;
        if (shouldLogin(authenticatedUser)){
            token = tokenService.createToken(
                    authenticatedUser.getUsername(),
                    authenticatedUser.getConfirmed());
        }
        return token;
    }

    private boolean shouldLogin(ApplicationUser user) {
        return user != null;
    }

}
