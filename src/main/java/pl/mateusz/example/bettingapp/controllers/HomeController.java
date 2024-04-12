package pl.mateusz.example.bettingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mateusz.example.bettingapp.dto.MatchListDto;
import pl.mateusz.example.bettingapp.services.MatchService;

import java.util.List;

@Controller
public class HomeController {

    private final MatchService matchService;

    public HomeController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<MatchListDto> topMatches = matchService.getTop3Matches();
        model.addAttribute("matches", topMatches);
        return "home";
    }
}
