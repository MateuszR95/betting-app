package pl.mateusz.example.bettingapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mateusz.example.bettingapp.dto.BetDto;
import pl.mateusz.example.bettingapp.dto.MatchDto;
import pl.mateusz.example.bettingapp.entities.Bet;
import pl.mateusz.example.bettingapp.entities.Match;
import pl.mateusz.example.bettingapp.repositories.BetRepository;
import pl.mateusz.example.bettingapp.repositories.MatchRepository;
import pl.mateusz.example.bettingapp.repositories.UserRepository;

import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Service
public class BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public BetService(BetRepository betRepository, UserRepository userRepository, MatchRepository matchRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional
    public void addBet(BetDto betDto) {
        Bet bet = new Bet();
        bet.setBetAmount(betDto.getBetAmount());
        bet.setUserBet(betDto.getUserBet());
        bet.setUser(betDto.getUser());
        bet.setId(betDto.getId());
        bet.setMatch(betDto.getMatch());
        betRepository.save(bet);
    }

    public List<MatchDto> findMostBetsMatches() {
        List<Bet> allBets = betRepository.getAllBy();
        Map<Match, Long> betsPerMatch = allBets.stream()
                .collect(Collectors.groupingBy(Bet::getMatch, Collectors.counting()));
        List<Match> top3MostBetMatches = betsPerMatch.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        return top3MostBetMatches.stream().map(MatchService::convertToDto).collect(Collectors.toList());
    }

    public List<BetDto> getAllBets() {
        List<Bet> allBets = betRepository.getAllBy();
        return allBets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private BetDto convertToDto(Bet bet) {
        return new BetDto(bet.getId(), bet.getMatch(), bet.getUser(), bet.getUserBet(), bet.getBetAmount());
    }

}
