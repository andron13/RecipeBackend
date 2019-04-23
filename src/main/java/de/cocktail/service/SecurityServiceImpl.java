package de.cocktail.service;

import de.cocktail.configSecurity.JwtTokenProvider;
import de.cocktail.web.AuthenticationRequest;
import de.cocktail.web.UserCurent;
import de.cocktail.model.UserLogin;
import de.cocktail.repository.UserRepository;
import de.exeption.PermissionDeny;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final
    AuthenticationManager authenticationManager;

    private final
    JwtTokenProvider jwtTokenProvider;

    public SecurityServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public void runSavet(UserLogin userLogin) {
        if (!userRepository.findByUsername(userLogin.getUsername()).isPresent()) {
                this.userRepository.save(UserLogin.builder()
                        .username(userLogin.getUsername())
                        .email(userLogin.getEmail())
                        .password(this.passwordEncoder.encode(userLogin.getPassword()))
                        .roles(new ArrayList<String>(Collections.singleton("ROLE_USER")))
                        .build());

        } else throw   new PermissionDeny( "User is Present");

    }

    public UserCurent currentUser(UserDetails userDetails) {
        return new UserCurent(userDetails.getUsername(),
                new ArrayList<String>(userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(toList())));
    }

    public Map<String, String> logIn(AuthenticationRequest data) {
        String username = data.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
        Optional<UserLogin> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            String token = jwtTokenProvider.createToken(username, byUsername.get().getRoles());

            Map<String, String> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return model;
        } else {
            log.warn("Username " + username + "not found");
            new UsernameNotFoundException("Username " + username + "not found");
            return null;
        }
    }
    public void clouseAccountUserByName(String username){
        Optional<UserLogin> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()){
            userRepository.deleteById(byUsername.get().getId());
        }else{
            log.warn("Username " + username + "not found");
            throw new UsernameNotFoundException("Username " + username + "not found");
        }

    }
}
