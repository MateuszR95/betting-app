package pl.mateusz.example.bettingapp.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id")
    private Match match;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    private int userBet; //0 - remis, 1- zwycięstwo gospodarza, 2- zwyciestwo gości
    private BigDecimal betAmount;

    public Bet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
