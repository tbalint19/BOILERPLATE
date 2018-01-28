package com.base.userClient.repository.userAuth;

import com.base.userClient.model.userAuth.Confirmation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends CrudRepository<Confirmation, Long> {

}
