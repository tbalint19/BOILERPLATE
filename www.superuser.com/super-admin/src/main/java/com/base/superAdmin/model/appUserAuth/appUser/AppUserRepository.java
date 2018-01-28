package com.base.superAdmin.model.appUserAuth.appUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByUsername(String username);

    AppUser findByEmail(String email);

    AppUser findByUsernameIgnoreCase(String username);

    AppUser findByEmailIgnoreCase(String email);
}
