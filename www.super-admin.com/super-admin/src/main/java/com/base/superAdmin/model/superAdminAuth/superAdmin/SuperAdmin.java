package com.base.superAdmin.model.superAdminAuth.superAdmin;

import com.base.superAdmin.model.superAdminAuth.superAdminPasswordReset.SuperAdminPasswordReset;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.security.model.AuthEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class SuperAdmin implements AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "superAdmin", fetch = FetchType.LAZY)
    private List<SuperAdminPasswordReset> superAdminPasswordResets;

    @Override
    public Boolean getConfirmed() {
        return true;
    }

    @Override
    public Boolean getActive() {
        return true;
    }
}
