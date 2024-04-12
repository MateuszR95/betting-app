package pl.mateusz.example.bettingapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mateusz.example.bettingapp.dto.BetDto;
import pl.mateusz.example.bettingapp.entities.Bet;
import pl.mateusz.example.bettingapp.entities.User;
import pl.mateusz.example.bettingapp.repositories.BetRepository;
import pl.mateusz.example.bettingapp.repositories.MatchRepository;
import pl.mateusz.example.bettingapp.repositories.UserRepository;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;

    public BetService(BetRepository betRepository, UserRepository userRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long addBet(BetDto betDto) {
        Bet bet = new Bet();
        String userEmail = betDto.getUser().getEmail();
        Optional<User> userOptional = userRepository.getUserByEmail(userEmail);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            bet.setUser(existingUser);
        } else {
            User newUser = betDto.getUser();
            userRepository.save(newUser);
            bet.setUser(newUser);
        }
        bet.setBetAmount(betDto.getBetAmount());
        bet.setUserBet(betDto.getUserBet());
        bet.setId(betDto.getId());
        bet.setMatch(betDto.getMatch());
        betRepository.save(bet);
        return bet.getId();
    }

    public Optional<BetDto> getBetById(Long id) {
        return betRepository.getBetById(id).map(this::convertToDto);
    }

    public List<BetDto> getAllBets() {
        List<Bet> allBets = betRepository.getAllBy();
        return allBets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public BetDto convertToDto(Bet bet) {
        return new BetDto(bet.getId(), bet.getMatch(), bet.getUser(), bet.getUserBet(), bet.getBetAmount());
    }

}
