package com.base.usermaintenance.job;


import com.base.usermaintenance.model.appUserAuth.appUser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UnconfirmedUserManager {

    @Autowired
    private AppUserRepository userRepository;

    @Value("@{monitor.delete.user}")
    private long USER_DELETE_DELAY;

    private static final long RUNNING_DELAY = 60000;

    @Scheduled(fixedDelay = RUNNING_DELAY)
    public void deleteUnconfirmed(){
        boolean status = false;
        long current = new Date().getTime();
        Date date = new Date();
        date.setTime(current - USER_DELETE_DELAY);
        userRepository.deleteAppUserByConfirmedAndCreatedBefore(status, date);
    }
}
