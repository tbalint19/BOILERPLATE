package com.base.defaultAdmin.model.appUserAuth.appUser;


import com.base.defaultAdmin.model.appUserAuth.appUserConfirmation.AppUserConfirmation;
import com.base.defaultAdmin.model.appUserAuth.appUserPasswordReset.AppUserPasswordReset;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.security.model.AuthEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class AppUser implements AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private Boolean confirmed;

    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY)
    private AppUserConfirmation appUserConfirmation;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private List<AppUserPasswordReset> appUserPasswordResets;

}
