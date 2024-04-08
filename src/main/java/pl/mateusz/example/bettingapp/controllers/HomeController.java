package pl.mateusz.example.bettingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mateusz.example.bettingapp.dto.MatchDto;
import pl.mateusz.example.bettingapp.services.BetService;

import java.util.List;

@Controller
public class HomeController {

    private final BetService betService;

    public HomeController(BetService betService) {
        this.betService = betService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<MatchDto> mostBetsMatches = betService.findMostBetsMatches();
        model.addAttribute("matches", mostBetsMatches);
        return "home";
    }
}
