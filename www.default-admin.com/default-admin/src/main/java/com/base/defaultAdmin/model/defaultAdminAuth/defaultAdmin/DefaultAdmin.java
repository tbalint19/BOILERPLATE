package com.base.defaultAdmin.model.defaultAdminAuth.defaultAdmin;

import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdminPasswordReset.DefaultAdminPasswordReset;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.security.model.AuthEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class DefaultAdmin implements AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Boolean active;

    @OneToMany(mappedBy = "defaultAdmin")
    private List<DefaultAdminPasswordReset> defaultAdminPasswordResets;

    @Override
    public Boolean getConfirmed() {
        return true;
    }
}
