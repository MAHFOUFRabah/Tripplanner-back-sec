package io.aperofy.service.security;

import io.aperofy.dao.security.AppRoleRepository;
import io.aperofy.dao.security.UserRepository;
import io.aperofy.entities.security.AppRole;
import io.aperofy.entities.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(UserRepository userRepository, AppRoleRepository appRoleRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.userRepository = userRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword) {
        AppUser user = this.userRepository.findByUsername(username);
        if (user != null)
            throw new RuntimeException("Utilisateur Existe Déjà");
        if (!password.equals(confirmedPassword))
            throw new RuntimeException("Please confirm your password");
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setActivated(true);
        appUser.setPassword(this.bCryptPasswordEncoder.encode(password));
        user = this.userRepository.save(appUser);
        addRoleToUser(username, "USER");
        return user;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return this.appRoleRepository.save(role);

    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return this.userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppUser appUser = userRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(rolename);
        appUser.getRoles().add(appRole);
    }

}
