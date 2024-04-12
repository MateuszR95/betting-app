package pl.mateusz.example.bettingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.example.bettingapp.entities.Bet;
import pl.mateusz.example.bettingapp.entities.Match;

import java.util.List;
import java.util.Optional;

public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllByMatch(Match match);

    List<Bet> getAllBy();
    Optional<Bet> getBetById(Long id);



}
