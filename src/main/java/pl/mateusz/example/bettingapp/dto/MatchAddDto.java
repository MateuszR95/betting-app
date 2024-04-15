package pl.mateusz.example.bettingapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MatchAddDto {

    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime;
    private BigDecimal oddsOnHomeTeamToWin;
    private BigDecimal oddsOnAwayTeamToWin;
    private BigDecimal oddsOnDraw;

    public MatchAddDto(Long id, Long homeTeamId, Long awayTeamId, LocalDateTime dateTime,
                       BigDecimal oddsOnHomeTeamToWin, BigDecimal oddsOnAwayTeamToWin, BigDecimal oddsOnDraw) {
        this.id = id;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
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

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
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
