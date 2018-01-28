package com.base.userClient.service.auth;

import com.base.userClient.model.userAuth.ApplicationUser;
import com.base.userClient.model.userAuth.Reset;
import com.base.userClient.repository.userAuth.ResetRepository;
import com.base.userClient.service.common.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResetService {

    @Autowired
    private RandomService randomService;

    @Autowired
    private ResetRepository resetRepository;

    public Reset createReset(ApplicationUser user){
        if (user == null){
            return null;
        }
        Reset reset = new Reset();
        reset.setCode(randomService.getRandomString(25));
        reset.setUserId(user.getId());
        reset.setUsed(false);
        reset.setCreated(new Date());
        resetRepository.save(reset);
        return reset;
    }

    public Boolean applyReset(ApplicationUser user, String code){
        Boolean successful = false;
        Reset reset = resetRepository.findByCode(code);
        if (belongsToUser(reset, user)){
            if (!reset.getUsed()){
                reset.setUsed(true);
                resetRepository.save(reset);
                successful = true;
            }
        }
        return successful;
    }

    private boolean belongsToUser(Reset reset, ApplicationUser user){
        return reset != null && reset.getUserId().equals(user.getId());
    }

}
