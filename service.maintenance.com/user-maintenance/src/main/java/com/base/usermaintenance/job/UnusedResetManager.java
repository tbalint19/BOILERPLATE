package com.base.usermaintenance.job;

import com.base.usermaintenance.model.appUserAuth.appUserPasswordReset.AppUserPasswordResetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UnusedResetManager {

    @Autowired
    private AppUserPasswordResetRepository resetRepository;

    @Value("@{monitor.delete.reset}")
    private Long RESET_DELETE_DELAY;

    private static final long RUNNING_DELAY = 60000;

    @Scheduled(fixedDelay = RUNNING_DELAY)
    public void deleteUnconfirmed(){
        boolean status = false;
        long current = new Date().getTime();
        Date date = new Date();
        date.setTime(current - RESET_DELETE_DELAY);
        resetRepository.deleteAppUserPasswordResetByUsedAndCreatedBefore(status, date);
    }
}
