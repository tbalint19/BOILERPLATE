package com.base.defaultAdmin.model.defaultAdminAuth.defaultAdmin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefaultAdminRepository extends CrudRepository<DefaultAdmin, Long> {

    List<DefaultAdmin> findAll();

    DefaultAdmin findByUsername(String username);
}
