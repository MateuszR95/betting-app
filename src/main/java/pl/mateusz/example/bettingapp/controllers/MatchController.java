package pl.mateusz.example.bettingapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.example.bettingapp.dto.MatchAddDto;
import pl.mateusz.example.bettingapp.dto.MatchEditDto;
import pl.mateusz.example.bettingapp.dto.MatchListDto;
import pl.mateusz.example.bettingapp.dto.TeamDto;
import pl.mateusz.example.bettingapp.exceptions.DuplicateTeamInMatchException;
import pl.mateusz.example.bettingapp.exceptions.MatchNotFoundException;
import pl.mateusz.example.bettingapp.exceptions.NegativeScoreException;
import pl.mateusz.example.bettingapp.exceptions.PastDateException;
import pl.mateusz.example.bettingapp.services.MatchService;
import pl.mateusz.example.bettingapp.services.TeamService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class MatchController {
    private final MatchService matchService;
    private final TeamService teamService;
    private final Logger logger = LoggerFactory.getLogger(MatchController.class);

    public MatchController(MatchService matchService, TeamService teamService) {
        this.matchService = matchService;
        this.teamService = teamService;
    }

    @GetMapping("/form")
    public String displayAddingForm(Model model) {
        List<TeamDto> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("error", null);
        return "match-adding-form";
    }

    @PostMapping("/add")
    public String addMatch(MatchAddDto matchAddDto, Model model) {
        try {
            matchService.addMatch(matchAddDto);
        } catch (PastDateException | DuplicateTeamInMatchException e) {
//            System.out.println(e.getMessage());
            logger.info(e.getMessage());
            model.addAttribute("error", e.getMessage());
            List<TeamDto> teams = teamService.getAllTeams();
            model.addAttribute("teams", teams);
            return "match-adding-form";
        }
        return "redirect:/";
    }

    @GetMapping("/list")
    public String getComingMatches(Model model) {
        List<MatchListDto> comingMatches = matchService.getComingMatches();
        model.addAttribute("matches", comingMatches);
        return "list";
    }

    @GetMapping("/archive")
    public String getFinishedMatches(Model model) {
        List<MatchListDto> finishedMatches = matchService.getFinishedMatches();
        model.addAttribute("matches", finishedMatches);
        return "archive";
    }

    @GetMapping("/match/{id}/edit")
    public String displayEditionForm(@PathVariable Long id, Model model) {
        Optional<MatchEditDto> matchDtoOptional = matchService.getMatchEditDtoById(id);
        MatchEditDto matchEditDto = matchDtoOptional.orElseThrow(MatchNotFoundException::new);
        String homeTeamName = teamService.getTeamNameById(matchEditDto.getHomeTeamId());
        String awayTeamName = teamService.getTeamNameById(matchEditDto.getAwayTeamId());
        List<TeamDto> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        model.addAttribute("match", matchEditDto);
        model.addAttribute("homeTeamName", homeTeamName);
        model.addAttribute("awayTeamName", awayTeamName);
        model.addAttribute("error", null);
        return "edit-form";
    }

    @PostMapping("/match/{id}/edit")
    public String editMatch(@PathVariable Long id, MatchEditDto matchEditDto, Model model) {
        try {
            matchService.editMatch(id, matchEditDto);
        } catch (NegativeScoreException e) {
//            System.out.println(e.getMessage());
            logger.info(e.getMessage());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("match", matchEditDto);
            return "edit-form";
        }
        if (matchEditDto.getDateTime().isBefore(LocalDateTime.now())) {
            return "redirect:/archive";
        }
        return "redirect:/list";
    }

    @PostMapping("/match/{id}/delete")
    public String deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return "redirect:/list";
    }
}