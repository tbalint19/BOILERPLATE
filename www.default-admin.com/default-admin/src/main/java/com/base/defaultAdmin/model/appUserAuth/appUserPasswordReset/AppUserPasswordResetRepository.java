package com.base.defaultAdmin.model.appUserAuth.appUserPasswordReset;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserPasswordResetRepository extends CrudRepository<AppUserPasswordReset, Long> {

    AppUserPasswordReset findByCode(String code);
}
