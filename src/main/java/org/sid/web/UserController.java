package org.sid.web;

import lombok.Data;
import org.sid.entities.AppUser;
import org.sid.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userFor) {
        return accountService.saveUser(userFor.getUsername(), userFor.getPassword(), userFor.getConfirmedPassword(), UUID.randomUUID());
    }
}

@Data
class UserForm {
    private String username;
    private String password;
    private String confirmedPassword;

}
