package pl.lukaszg.lookmatches.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByPrincipalId(String principalId);
    User findByEmail(String email);

    User findByEmail(Object email);



}
