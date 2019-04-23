package de.cocktail.controller;

import de.cocktail.model.UserLogin;
import de.cocktail.repository.UserRepository;
import de.cocktail.service.SecurityService;
import de.cocktail.web.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthController {

    @Autowired
    SecurityService securityService;


    @Autowired
    UserRepository users;

    @PostMapping(value = "login/")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {
        return ResponseEntity.ok().body(securityService.logIn(data));
    }

    @PostMapping(value = "user-register/")
    public ResponseEntity createUser(@RequestBody UserLogin userLogin) {
        securityService.runSavet(userLogin);
        return ResponseEntity.ok().body("User Saved");
    }

    @DeleteMapping(value = "/closeaccount", params = "username")
    public ResponseEntity deleteByUserName(@RequestParam String username) {
        securityService.clouseAccountUserByName(username);
        return ResponseEntity.ok().build();
    }

}

