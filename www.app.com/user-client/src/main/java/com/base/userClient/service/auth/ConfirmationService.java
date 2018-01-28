package com.base.userClient.service.auth;

import com.base.userClient.model.userAuth.ApplicationUser;
import com.base.userClient.model.userAuth.Confirmation;
import com.base.userClient.repository.userAuth.ConfirmationRepository;
import com.base.userClient.repository.userAuth.UserRepository;
import com.base.userClient.service.common.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationService {

    @Autowired
    private RandomService randomService;

    @Autowired
    private ConfirmationRepository confirmationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public Confirmation createConfirmation(){
        Confirmation confirmation = new Confirmation();
        String code = randomService.getRandomString(25);
        confirmation.setCode(code);
        confirmationRepository.save(confirmation);
        return confirmation;
    }

    public String attemptConfirm(String credential, String code){
        ApplicationUser user = userService.getUserByCredential(credential);
        if (user == null){
            return null;
        }
        if (!user.getConfirmation().getCode().equals(code) || user.getConfirmed()){
            return null;
        }
        confirm(user);
        return tokenService.createToken(user.getUsername(), user.getConfirmed());
    }

    private void confirm(ApplicationUser user){
        Confirmation confirmation = user.getConfirmation();
        confirmation.setUsed(true);
        confirmationRepository.save(confirmation);
        user.setConfirmed(true);
        userRepository.save(user);
    }

}
