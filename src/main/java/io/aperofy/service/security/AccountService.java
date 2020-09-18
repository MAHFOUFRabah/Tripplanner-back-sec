package io.aperofy.service.security;

import io.aperofy.entities.security.AppRole;
import io.aperofy.entities.security.AppUser;

public interface AccountService {
    AppUser saveUser(String username, String password, String confirmedPassword);

    AppRole saveRole(AppRole role);

    AppUser loadUserByUsername(String username);

    void addRoleToUser(String username, String rolename);

}
