package com.base.superAdmin.model.defaultAdminAuth.defaultAdminPasswordReset;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultAdminPasswordResetRepostory extends CrudRepository<DefaultAdminPasswordReset, Long> {
}
