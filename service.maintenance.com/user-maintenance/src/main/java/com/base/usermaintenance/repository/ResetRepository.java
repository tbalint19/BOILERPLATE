package com.base.usermaintenance.repository;

import com.base.usermaintenance.model.Reset;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface ResetRepository extends CrudRepository<Reset, Long> {

    @Transactional
    void deleteResetByUsedAndCreatedBefore(Boolean confirmed, Date date);
}
