package com.base.superAdmin.model.superAdminAuth.superAdmin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepository extends CrudRepository<SuperAdmin, Long> {

    SuperAdmin findByUsername(String username);
}
