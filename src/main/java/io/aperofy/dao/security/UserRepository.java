package io.aperofy.dao.security;

import io.aperofy.entities.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String userName);

}
