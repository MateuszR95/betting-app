package pl.mateusz.example.bettingapp.services;

import org.springframework.stereotype.Service;
import pl.mateusz.example.bettingapp.dto.TeamDto;
import pl.mateusz.example.bettingapp.entities.Team;
import pl.mateusz.example.bettingapp.exceptions.TeamNotFoundException;
import pl.mateusz.example.bettingapp.repositories.TeamRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> getAllTeams() {
        return teamRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());

    }

    public TeamDto convertToDto(Team team) {
        return new TeamDto(team.getId(), team.getName());
    }

    public String getTeamNameById(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        return teamOptional.map(Team::getName).orElseThrow(TeamNotFoundException::new);
    }
}
