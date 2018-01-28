package com.base.superAdmin.model.defaultAdminAuth.defaultAdminPasswordReset;

import com.base.superAdmin.model.defaultAdminAuth.defaultAdmin.DefaultAdmin;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
