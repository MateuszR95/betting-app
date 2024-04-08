package pl.mateusz.example.bettingapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mateusz.example.bettingapp.dto.MatchDto;
import pl.mateusz.example.bettingapp.entities.Bet;
import pl.mateusz.example.bettingapp.entities.Match;
import pl.mateusz.example.bettingapp.exceptions.DuplicateTeamInMatchException;
import pl.mateusz.example.bettingapp.exceptions.MatchNotFoundException;
import pl.mateusz.example.bettingapp.exceptions.NegativeScoreException;
import pl.mateusz.example.bettingapp.exceptions.PastDateException;
import pl.mateusz.example.bettingapp.repositories.BetRepository;
import pl.mateusz.example.bettingapp.repositories.MatchRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final BetRepository betRepository;

    private static final int DEFAULT_TEAMS_SCORE = -1;

    public MatchService(MatchRepository matchRepository, BetRepository betRepository) {
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
    }

    @Transactional
    public void addMatch(MatchDto matchDto) {
        Match match = new Match();
        if (matchDto.getDateTime().isBefore(LocalDateTime.now())) {
            throw new PastDateException("Nie można dodać meczu, który się zaczął");
        }
        if (matchDto.getHomeTeam().equals(matchDto.getAwayTeam())) {
            throw new DuplicateTeamInMatchException("W jednym meczu nie mogą grać dwie takie same drużyny");
        }
        match.setId(matchDto.getId());
        match.setHomeTeam(matchDto.getHomeTeam());
        match.setHomeTeamScore(matchDto.getHomeTeamScore());
        match.setAwayTeam(matchDto.getAwayTeam());
        match.setAwayTeamScore(matchDto.getAwayTeamScore());
        match.setDateTime(matchDto.getDateTime());
        match.setOddsOnHomeTeamToWin(matchDto.getOddsOnHomeTeamToWin());
        match.setOddsOnAwayTeamToWin(matchDto.getOddsOnAwayTeamToWin());
        match.setOddsOnDraw(matchDto.getOddsOnDraw());
        matchRepository.save(match);
    }

    public List<MatchDto> getComingMatches() {
        List<Match> matchList = matchRepository.findAllByDateTimeIsAfter(LocalDateTime.now());
        return matchList.stream().map(MatchService::convertToDto).collect(Collectors.toList());
    }

    public List<MatchDto> getFinishedMatches() {
        List<Match> allFinishedMatches = matchRepository.findAllByDateTimeIsBefore(LocalDateTime.now());
        return allFinishedMatches.stream().map(MatchService::convertToDto).collect(Collectors.toList());
    }

    public Optional<MatchDto> getMatchById(Long id) {
        return matchRepository.getMatchById(id).map(MatchService::convertToDto);
    }

    public static MatchDto convertToDto(Match match) {
        return new MatchDto(match.getId(), match.getHomeTeam(), match.getHomeTeamScore(), match.getAwayTeam(),
                match.getAwayTeamScore(), match.getDateTime(),
                match.getOddsOnHomeTeamToWin(), match.getOddsOnAwayTeamToWin(), match.getOddsOnDraw());
    }

    @Transactional
    public void editMatch(Long id, MatchDto matchDto) {
        Optional<Match> optionalMatch = matchRepository.getMatchById(id);
        Match match = optionalMatch.orElseThrow(MatchNotFoundException::new);

        if (matchDto.getHomeTeamScore() < DEFAULT_TEAMS_SCORE || matchDto.getAwayTeamScore() < DEFAULT_TEAMS_SCORE) {
            throw new NegativeScoreException("Wynik meczu nie może być ujemny");
        }
        match.setId(matchDto.getId());
        match.setHomeTeam(matchDto.getHomeTeam());
        match.setAwayTeam(matchDto.getAwayTeam());
        match.setDateTime(matchDto.getDateTime());
        match.setHomeTeamScore(matchDto.getHomeTeamScore());
        match.setAwayTeamScore(matchDto.getAwayTeamScore());
        matchRepository.save(match);
    }

    @Transactional
    public void deleteMatch(Long id) {
        Optional<Match> optionalMatch = matchRepository.getMatchById(id);
        Match match = optionalMatch.orElseThrow(MatchNotFoundException::new);
        List<Bet> bets = betRepository.findAllByMatch(match);
        betRepository.deleteAll(bets);
        matchRepository.delete(match);
    }

}

