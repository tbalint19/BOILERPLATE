package com.base.userClient.repository.userAuth;

import com.base.userClient.model.userAuth.Reset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetRepository extends CrudRepository<Reset, Long> {

    Reset findByCode(String code);
}
