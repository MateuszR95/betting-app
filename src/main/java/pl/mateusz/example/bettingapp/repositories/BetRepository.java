package pl.mateusz.example.bettingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.example.bettingapp.entities.Bet;
import pl.mateusz.example.bettingapp.entities.Match;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllByMatch(Match match);

    List<Bet> getAllBy();


}
