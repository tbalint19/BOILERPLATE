package com.base.userClient.model.appUserAuth.appUserPasswordReset;

import com.base.userClient.model.appUserAuth.appUser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class AppUserPasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Boolean used;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne
    private AppUser appUser;

    public AppUserPasswordReset(AppUser appUser) {
        this.appUser = appUser;
        this.used = false;
    }
}
