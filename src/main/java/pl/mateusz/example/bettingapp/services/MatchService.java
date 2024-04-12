package pl.mateusz.example.bettingapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mateusz.example.bettingapp.dto.MatchAddDto;
import pl.mateusz.example.bettingapp.dto.MatchEditDto;
import pl.mateusz.example.bettingapp.dto.MatchListDto;
import pl.mateusz.example.bettingapp.entities.Bet;
import pl.mateusz.example.bettingapp.entities.Match;
import pl.mateusz.example.bettingapp.entities.Team;
import pl.mateusz.example.bettingapp.exceptions.DuplicateTeamInMatchException;
import pl.mateusz.example.bettingapp.exceptions.MatchNotFoundException;
import pl.mateusz.example.bettingapp.exceptions.NegativeScoreException;
import pl.mateusz.example.bettingapp.exceptions.PastDateException;
import pl.mateusz.example.bettingapp.repositories.BetRepository;
import pl.mateusz.example.bettingapp.repositories.MatchRepository;
import pl.mateusz.example.bettingapp.repositories.TeamRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final BetRepository betRepository;
    private final TeamRepository teamRepository;

    public MatchService(MatchRepository matchRepository, BetRepository betRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void addMatch(MatchAddDto matchAddDto) {
        Match match = new Match();
        Optional<Team> homeTeamOptional = teamRepository.getTeamById(matchAddDto.getHomeTeamId());
        Optional<Team> awayTeamOptional = teamRepository.getTeamById(matchAddDto.getAwayTeamId());
        if (matchAddDto.getDateTime().isBefore(LocalDateTime.now())) {
            throw new PastDateException("Nie można dodać meczu z przeszłą datą");
        }
        if (matchAddDto.getHomeTeamId().equals(matchAddDto.getAwayTeamId())) {
            throw new DuplicateTeamInMatchException("W jednym meczu nie mogą grać dwie takie same drużyny");
        }
        match.setId(matchAddDto.getId());
        homeTeamOptional.ifPresent(match::setHomeTeam);
        match.setHomeTeamScore(null);
        awayTeamOptional.ifPresent(match::setAwayTeam);
        match.setAwayTeamScore(null);
        match.setDateTime(matchAddDto.getDateTime());
        match.setOddsOnHomeTeamToWin(matchAddDto.getOddsOnHomeTeamToWin());
        match.setOddsOnAwayTeamToWin(matchAddDto.getOddsOnAwayTeamToWin());
        match.setOddsOnDraw(matchAddDto.getOddsOnDraw());
        matchRepository.save(match);

    }

    public List<MatchListDto> getComingMatches() {
        List<Match> allComingMatches = matchRepository.findAllByDateTimeIsAfter(LocalDateTime.now());
        return allComingMatches.stream().map(MatchService::convertToMatchListDto).collect(Collectors.toList());
    }

    public List<MatchListDto> getFinishedMatches() {
        List<Match> allFinishedMatches = matchRepository.findAllByDateTimeIsBefore(LocalDateTime.now());
        return allFinishedMatches.stream().map(MatchService::convertToMatchListDto).collect(Collectors.toList());
    }

    public Optional<MatchListDto> getMatchListDtoById(Long id) {
        return matchRepository.getMatchById(id).map(MatchService::convertToMatchListDto);
    }

    public Optional<MatchEditDto> getMatchEditDtoById(Long id) {
        return matchRepository.getMatchById(id).map(MatchService::convertToMatchEditDto);
    }

    public List<MatchListDto> getTop3Matches() {
        List<Match> topMatches = matchRepository.getTopMatches();
        return topMatches.stream().map(MatchService::convertToMatchListDto).collect(Collectors.toList());
    }

    public static MatchListDto convertToMatchListDto(Match match) {
       return new MatchListDto(match.getId(), match.getHomeTeam(), match.getHomeTeamScore(), match.getAwayTeam(),
               match.getAwayTeamScore(), match.getDateTime(), match.getOddsOnHomeTeamToWin(),
               match.getOddsOnAwayTeamToWin(), match.getOddsOnDraw());
    }

    public static MatchEditDto convertToMatchEditDto(Match match) {
        return new MatchEditDto(match.getId(), match.getHomeTeam().getId(), match.getAwayTeam().getId(),
                match.getDateTime(), match.getHomeTeamScore(), match.getAwayTeamScore());
    }

    @Transactional
    public void editMatch(Long id, MatchEditDto matchEditDto) {

        if (matchEditDto.getHomeTeamScore() != null && matchEditDto.getHomeTeamScore() < 0 ||
                matchEditDto.getAwayTeamScore() != null && matchEditDto.getAwayTeamScore() < 0) {
            throw new NegativeScoreException("Wynik meczu nie może być ujemny");
        }
        Optional<Match> optionalMatch = matchRepository.getMatchById(id);
        Match match = optionalMatch.orElseThrow(MatchNotFoundException::new);
        Optional<Team> homeTeamOptional = teamRepository.getTeamById(matchEditDto.getHomeTeamId());
        Optional<Team> awayTeamOptional = teamRepository.getTeamById(matchEditDto.getAwayTeamId());

        match.setId(matchEditDto.getId());
        homeTeamOptional.ifPresent(match::setHomeTeam);
        awayTeamOptional.ifPresent(match::setAwayTeam);
        match.setDateTime(matchEditDto.getDateTime());
        match.setHomeTeamScore(matchEditDto.getHomeTeamScore());
        match.setAwayTeamScore(matchEditDto.getAwayTeamScore());
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

