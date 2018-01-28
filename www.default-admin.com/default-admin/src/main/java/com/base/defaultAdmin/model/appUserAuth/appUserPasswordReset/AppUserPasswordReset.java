package com.base.defaultAdmin.model.appUserAuth.appUserPasswordReset;

import com.base.defaultAdmin.model.appUserAuth.appUser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class AppUserPasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private Boolean used;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne
    private AppUser appUser;
}
