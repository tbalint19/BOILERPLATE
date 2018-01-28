package com.base.superAdmin.model.superAdminAuth.superAdminPasswordReset;

import com.base.superAdmin.model.superAdminAuth.superAdmin.SuperAdmin;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.security.service.RandomService;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class SuperAdminPasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Boolean used;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne
    private SuperAdmin superAdmin;

    public SuperAdminPasswordReset(SuperAdmin superAdmin) {
        this.superAdmin = superAdmin;
        this.code = new RandomService().getRandomString(25);
        this.used = false;
    }
}
