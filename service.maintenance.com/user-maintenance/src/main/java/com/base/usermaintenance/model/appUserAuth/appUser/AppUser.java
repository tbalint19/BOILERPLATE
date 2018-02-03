package com.base.usermaintenance.model.appUserAuth.appUser;


import com.base.userClient.model.appUserAuth.appUserConfirmation.AppUserConfirmation;
import com.base.userClient.model.appUserAuth.appUserPasswordReset.AppUserPasswordReset;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AppUserConfirmation appUserConfirmation;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AppUserPasswordReset> appUserPasswordResets;

    public AppUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmed = false;
        this.active = true;
    }

}
