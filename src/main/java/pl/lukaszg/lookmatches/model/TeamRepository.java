package pl.lukaszg.lookmatches.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Optional<Team> findById(Long teamId);

    String findByName(String name);
}
