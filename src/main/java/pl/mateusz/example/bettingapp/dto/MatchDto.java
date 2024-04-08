package pl.mateusz.example.bettingapp.dto;

import jakarta.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mateusz.example.bettingapp.entities.Bet;
import pl.mateusz.example.bettingapp.entities.Team;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MatchDto {
    private Long id;
    @ManyToOne
    private Team homeTeam;
    private int homeTeamScore;
    @ManyToOne
    private Team awayTeam;
    private int awayTeamScore;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;
    private BigDecimal oddsOnHomeTeamToWin;
    private BigDecimal oddsOnAwayTeamToWin;
    private BigDecimal oddsOnDraw;
    private List<Bet> bets;

    public MatchDto(Long id, Team homeTeam, int homeTeamScore, Team awayTeam, int awayTeamScore,
                    LocalDateTime dateTime, BigDecimal oddsOnHomeTeamToWin, BigDecimal oddsOnAwayTeamToWin,
                    BigDecimal oddsOnDraw) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeam = awayTeam;
        this.awayTeamScore = awayTeamScore;
        this.dateTime = dateTime;
        this.oddsOnHomeTeamToWin = oddsOnHomeTeamToWin;
        this.oddsOnAwayTeamToWin = oddsOnAwayTeamToWin;
        this.oddsOnDraw = oddsOnDraw;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;

    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getOddsOnHomeTeamToWin() {
        return oddsOnHomeTeamToWin;
    }

    public void setOddsOnHomeTeamToWin(BigDecimal oddsOnHomeTeamToWin) {
        this.oddsOnHomeTeamToWin = oddsOnHomeTeamToWin;
    }

    public BigDecimal getOddsOnAwayTeamToWin() {
        return oddsOnAwayTeamToWin;
    }

    public void setOddsOnAwayTeamToWin(BigDecimal oddsOnAwayTeamToWin) {
        this.oddsOnAwayTeamToWin = oddsOnAwayTeamToWin;
    }

    public BigDecimal getOddsOnDraw() {
        return oddsOnDraw;
    }

    public void setOddsOnDraw(BigDecimal oddsOnDraw) {
        this.oddsOnDraw = oddsOnDraw;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}

