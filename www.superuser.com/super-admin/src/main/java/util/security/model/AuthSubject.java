package util.security.model;

import lombok.Data;

import java.util.Date;

@Data
public class AuthSubject {

    private Long id;

    private String username;

    private Boolean active;

    private Boolean confirmed;

    private Date created;

    public AuthSubject(AuthEntity authEntity) {
        this.id = authEntity.getId();
        this.username = authEntity.getUsername();
        this.active = authEntity.getActive();
        this.confirmed = authEntity.getConfirmed();
        this.created = new Date();
    }

}
