package com.base.usermaintenance.model.appUserAuth.appUserPasswordReset;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;

@Repository
public interface AppUserPasswordResetRepository extends CrudRepository<AppUserPasswordReset, Long> {

    AppUserPasswordReset findByCode(String code);

    void deleteAppUserPasswordResetByUsedAndCreatedBefore(Boolean status, Date date);
}
