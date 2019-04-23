package de.cocktail.service;

import de.cocktail.model.UserLogin;
import de.cocktail.web.AuthenticationRequest;
import de.cocktail.web.UserCurent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SecurityService {
    Map<String, String> logIn(AuthenticationRequest data);

    UserCurent currentUser(UserDetails userDetails);

    void runSavet(UserLogin userLogin);

    void clouseAccountUserByName(String username);
}
