package pl.mateusz.example.bettingapp.services;

import org.springframework.stereotype.Service;
import pl.mateusz.example.bettingapp.repositories.TeamRepository;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
