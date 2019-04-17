package de.cocktail.service;

import de.cocktail.model.UserLogin;
import de.cocktail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@Service
public class SqurityService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String runSavet(UserLogin userLogin) {
        if (!userRepository.findByUsername(userLogin.getUsername()).isPresent()) {
            if (userLogin.getRoles().contains("ROLE_USER")) {
                this.userRepository.save(UserLogin.builder()
                        .username(userLogin.getUsername())
                        .email(userLogin.getEmail())
                        .password(this.passwordEncoder.encode(userLogin.getPassword()))
                        .roles(userLogin.getRoles())
                        .build()
                );
            }
            if (userLogin.getRoles().contains("ROLE_ADMIN")) {

                this.userRepository.save(UserLogin.builder()
                        .username(userLogin.getUsername())
                        .email(userLogin.getEmail())
                        .password(this.passwordEncoder.encode(userLogin.getPassword()))
                        .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                        .build()
                );
            }
            return "user savet";
        } else return "user is Present";

    }

    public Map<Object, Object> currentUser(@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return model;
    }
    public
}
