package com.base.usermaintenance.model.appUserAuth.appUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    List<AppUser> findAll();

    AppUser findByUsername(String username);

    AppUser findByEmail(String email);

    void deleteAppUserByConfirmedAndCreatedBefore(Boolean status, Date date);
}
