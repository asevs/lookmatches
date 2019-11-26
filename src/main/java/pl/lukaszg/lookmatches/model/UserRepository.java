package pl.lukaszg.lookmatches.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByPrincipalId(String principalId);
    Optional<User> findByEmail(String email);

    User findByEmail(Object email);



}
