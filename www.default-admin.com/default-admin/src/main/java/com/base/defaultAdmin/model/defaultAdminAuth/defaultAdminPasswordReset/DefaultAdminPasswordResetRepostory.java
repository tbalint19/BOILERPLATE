package com.base.defaultAdmin.model.defaultAdminAuth.defaultAdminPasswordReset;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultAdminPasswordResetRepostory extends CrudRepository<DefaultAdminPasswordReset, Long> {

    DefaultAdminPasswordReset findByCode(String code);
}
