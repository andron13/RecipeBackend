package de.cocktail.repository;


import de.cocktail.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserLogin, Long> {

    Optional<UserLogin> findByUsername(String username);

}
