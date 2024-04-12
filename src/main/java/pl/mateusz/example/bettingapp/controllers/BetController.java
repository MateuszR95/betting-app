package pl.mateusz.example.bettingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.example.bettingapp.dto.BetDto;
import pl.mateusz.example.bettingapp.dto.MatchEditDto;
import pl.mateusz.example.bettingapp.dto.MatchListDto;
import pl.mateusz.example.bettingapp.exceptions.BetNotFoundException;
import pl.mateusz.example.bettingapp.exceptions.MatchNotFoundException;
import pl.mateusz.example.bettingapp.services.BetService;
import pl.mateusz.example.bettingapp.services.MatchService;

import java.util.List;
import java.util.Optional;

@Controller
public class BetController {

    private final BetService betService;
    private final MatchService matchService;


    public BetController(BetService betService, MatchService matchService) {
        this.betService = betService;
        this.matchService = matchService;
    }

    @GetMapping("/match/{id}/bet")
    public String displayBetForm(@PathVariable Long id, Model model) {
        Optional<MatchListDto> matchDtoOptional = matchService.getMatchListDtoById(id);
        MatchListDto matchDto = matchDtoOptional.orElseThrow(MatchNotFoundException::new);
        model.addAttribute("match", matchDto);
        BetDto betDto = new BetDto();
        model.addAttribute("bet", betDto);
        return "bet-form";
    }

    @PostMapping("/bet/submit")
    public String submitBet(BetDto betDto) {
        Long betId = betService.addBet(betDto);
        return "redirect:/bet/" + betId;
    }

    @GetMapping("/bet/{id}")
    public String viewBet(@PathVariable Long id, Model model) {
        Optional<BetDto> betOptional = betService.getBetById(id);
        BetDto betDto = betOptional.orElseThrow(BetNotFoundException::new);
        model.addAttribute("bet", betDto);
        return "bet-details";
    }

    @GetMapping("/bets")
    public String displayBets(Model model) {
        List<BetDto> allBets = betService.getAllBets();
        model.addAttribute("bets", allBets);
        return "bets";
    }


}