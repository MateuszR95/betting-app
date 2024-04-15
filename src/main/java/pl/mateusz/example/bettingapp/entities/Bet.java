package pl.mateusz.example.bettingapp.entities;

import jakarta.persistence.*;
import pl.mateusz.example.bettingapp.BetStatus;

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
    @Enumerated(EnumType.ORDINAL)
    private BetStatus userBet;
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

    public BetStatus getUserBet() {
        return userBet;
    }

    public void setUserBet(BetStatus userBet) {
        this.userBet = userBet;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }
}
