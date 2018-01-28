package com.base.superAdmin.model.superAdminAuth.superAdminPasswordReset;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminPasswordResetRepository extends CrudRepository<SuperAdminPasswordReset, Long> {

    SuperAdminPasswordReset findByCode(String code);
}
