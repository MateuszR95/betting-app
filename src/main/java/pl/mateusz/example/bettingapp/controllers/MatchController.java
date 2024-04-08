package pl.mateusz.example.bettingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.example.bettingapp.dto.MatchDto;
import pl.mateusz.example.bettingapp.entities.Team;
import pl.mateusz.example.bettingapp.exceptions.DuplicateTeamInMatchException;
import pl.mateusz.example.bettingapp.exceptions.MatchNotFoundException;
import pl.mateusz.example.bettingapp.exceptions.NegativeScoreException;
import pl.mateusz.example.bettingapp.exceptions.PastDateException;
import pl.mateusz.example.bettingapp.repositories.TeamRepository;
import pl.mateusz.example.bettingapp.services.MatchService;

import java.util.List;
import java.util.Optional;

@Controller
public class MatchController {
    private final MatchService matchService;
    private final TeamRepository teamRepository;

    public MatchController(MatchService matchService, TeamRepository teamRepository) {
        this.matchService = matchService;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/form")
    public String displayAddingForm(Model model) {
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        model.addAttribute("error", null);
        return "match-adding-form";
    }

    @PostMapping("/add")
    public String addMatch(MatchDto matchDto, Model model) {
        try {
            matchDto.setHomeTeamScore(-1);
            matchDto.setAwayTeamScore(-1);
            matchService.addMatch(matchDto);
        } catch (PastDateException | DuplicateTeamInMatchException e) {
            System.out.println(e.getMessage());
            model.addAttribute("error", e.getMessage());
            List<Team> teams = teamRepository.findAll();
            model.addAttribute("teams", teams);
            return "match-adding-form";
        }
        return "redirect:/";
    }

    @GetMapping("/list")
    public String getComingMatches(Model model) {
        List<MatchDto> comingMatches = matchService.getComingMatches();
        model.addAttribute("matches", comingMatches);
        return "list";
    }

    @GetMapping("/archive")
    public String getFinishedMatches(Model model) {
        List<MatchDto> finishedMatches = matchService.getFinishedMatches();
        model.addAttribute("matches", finishedMatches);
        return "archive";
    }

    @GetMapping("/match/{id}/edit")
    public String displayEditionForm(@PathVariable Long id, Model model) {
        Optional<MatchDto> matchOptional = matchService.getMatchById(id);
        MatchDto matchDto = matchOptional.orElseThrow(MatchNotFoundException::new);
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        model.addAttribute("match", matchDto);
        return "edit-form";
    }

    @PostMapping("/match/{id}/edit")
    public String editMatch(@PathVariable Long id, MatchDto matchDto) {
        try {
            matchService.editMatch(id, matchDto);
        } catch (NegativeScoreException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/list";
    }

    @GetMapping("/match/{id}/delete")
    public String deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return "redirect:/list";
    }
}