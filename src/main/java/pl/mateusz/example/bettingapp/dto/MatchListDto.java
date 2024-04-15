package pl.mateusz.example.bettingapp.dto;

import pl.mateusz.example.bettingapp.entities.Team;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MatchListDto {
    private Long id;
    private Team homeTeam;
    private Integer homeTeamScore;
    private Team awayTeam;
    private Integer awayTeamScore;
    private LocalDateTime dateTime;
    private BigDecimal oddsOnHomeTeamToWin;
    private BigDecimal oddsOnAwayTeamToWin;
    private BigDecimal oddsOnDraw;

    public MatchListDto(Long id, Team homeTeam, Integer homeTeamScore, Team awayTeam, Integer awayTeamScore,
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

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
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
}

