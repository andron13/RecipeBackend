package sequrity.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import sequrity.Sequrityweb.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
