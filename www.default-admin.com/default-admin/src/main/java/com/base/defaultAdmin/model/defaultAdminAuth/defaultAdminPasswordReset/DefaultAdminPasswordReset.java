package com.base.defaultAdmin.model.defaultAdminAuth.defaultAdminPasswordReset;

import com.base.defaultAdmin.model.defaultAdminAuth.defaultAdmin.DefaultAdmin;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.security.service.RandomService;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class DefaultAdminPasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String code;

    private Boolean used;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne
    private DefaultAdmin defaultAdmin;

    public DefaultAdminPasswordReset(DefaultAdmin defaultAdmin) {
        this.defaultAdmin = defaultAdmin;
        this.code = new RandomService().getRandomString(25);
        this.used = false;
    }
}
