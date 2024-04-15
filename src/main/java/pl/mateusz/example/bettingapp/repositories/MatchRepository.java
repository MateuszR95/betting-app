package pl.mateusz.example.bettingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mateusz.example.bettingapp.entities.Match;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findAllByDateTimeIsAfter(LocalDateTime dateTime);
    List<Match> findAllByDateTimeIsBefore(LocalDateTime dateTime);
    Optional<Match> getMatchById(Long id);

    @Query(value = "SELECT m.* FROM Match m LEFT JOIN Bet b ON m.id = b.match_id GROUP BY m.id ORDER BY COUNT(b.id) DESC LIMIT 3",
            nativeQuery = true)
    List<Match> getTopMatches();

}
