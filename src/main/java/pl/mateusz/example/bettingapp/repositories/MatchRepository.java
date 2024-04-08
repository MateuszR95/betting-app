package pl.mateusz.example.bettingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.example.bettingapp.entities.Match;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findAllByDateTimeIsAfter(LocalDateTime dateTime);
    List<Match> findAllByDateTimeIsBefore(LocalDateTime dateTime);
    Optional<Match> getMatchById(Long id);

}
