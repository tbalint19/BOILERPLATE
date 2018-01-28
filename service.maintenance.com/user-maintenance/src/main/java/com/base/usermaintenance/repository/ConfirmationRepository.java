package com.base.usermaintenance.repository;

import com.base.usermaintenance.model.Confirmation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends CrudRepository<Confirmation, Long> {
}
