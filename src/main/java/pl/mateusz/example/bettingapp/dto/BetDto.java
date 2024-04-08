package pl.mateusz.example.bettingapp.dto;

import pl.mateusz.example.bettingapp.entities.Match;
import pl.mateusz.example.bettingapp.entities.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class BetDto {

    private Long id;
    private Match match;
    private User user;
    private int userBet;
    private BigDecimal betAmount;
    private static final int DEFAULT_TEAMS_SCORE = -1;
    private static final int USER_BET_ON_DRAW = 0;
    private static final int USER_BET_ON_HOME_TEAM_WIN = 1;
    private static final int USER_BET_ON_AWAY_TEAM_WIN = 2;


    public BetDto() {
    }

    public BetDto(Long id, Match match, User user, int userBet, BigDecimal betAmount) {
        this.id = id;
        this.match = match;
        this.user = user;
        this.userBet = userBet;
        this.betAmount = betAmount;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserBet() {
        return userBet;
    }

    public void setUserBet(int userBet) {
        this.userBet = userBet;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmountWon() {
        BigDecimal amountWon = BigDecimal.ZERO;
        int homeTeamScore = match.getHomeTeamScore();
        int awayTeamScore = match.getAwayTeamScore();

        if (homeTeamScore != DEFAULT_TEAMS_SCORE && awayTeamScore != DEFAULT_TEAMS_SCORE && match.getDateTime().isBefore(LocalDateTime.now())) {
            if (userBet == USER_BET_ON_HOME_TEAM_WIN) {
                if (homeTeamScore > awayTeamScore) {
                    amountWon = betAmount.multiply(match.getOddsOnHomeTeamToWin());
                } else if (homeTeamScore == awayTeamScore) {
                    amountWon = betAmount.multiply(match.getOddsOnHomeTeamToWin());
                }
            } else if (userBet == USER_BET_ON_AWAY_TEAM_WIN) {
                if (awayTeamScore > homeTeamScore) {
                    amountWon = betAmount.multiply(match.getOddsOnAwayTeamToWin());
                } else if (homeTeamScore == awayTeamScore) {
                    amountWon = betAmount.multiply(match.getOddsOnAwayTeamToWin());
                }
            } else if (userBet == USER_BET_ON_DRAW && homeTeamScore == awayTeamScore) {
                amountWon = betAmount.multiply(match.getOddsOnDraw());
            }
        }
        return amountWon.setScale(2, RoundingMode.CEILING);

    }
}
