package io.aperofy.web.security;

import io.aperofy.entities.security.AppUser;
import io.aperofy.service.security.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin
    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userFor) {
        return accountService.saveUser(userFor.getUsername(), userFor.getPassword(), userFor.getConfirmedPassword());
    }
}

@Data
class UserForm {
    private String username;
    private String password;
    private String confirmedPassword;

}
