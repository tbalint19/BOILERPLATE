package com.base.usermaintenance.model.appUserAuth.appUserConfirmation;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class AppUserConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;
    
    private Boolean used;

    public AppUserConfirmation() {
        this.used = false;
    }

}
