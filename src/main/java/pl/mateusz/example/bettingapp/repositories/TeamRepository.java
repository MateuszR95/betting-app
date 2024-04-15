package pl.mateusz.example.bettingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.example.bettingapp.entities.Team;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> getTeamById(Long id);
}
