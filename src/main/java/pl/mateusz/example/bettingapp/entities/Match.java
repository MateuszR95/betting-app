package pl.mateusz.example.bettingapp.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Team homeTeam;
    private Integer homeTeamScore;
    @ManyToOne
    private Team awayTeam;
    private Integer awayTeamScore;
    private LocalDateTime dateTime;
    private BigDecimal oddsOnHomeTeamToWin;
    private BigDecimal oddsOnAwayTeamToWin;
    private BigDecimal oddsOnDraw;
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<Bet> bets;

    public Match() {
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

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
