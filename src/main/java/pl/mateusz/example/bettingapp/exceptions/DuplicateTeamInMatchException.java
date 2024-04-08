package pl.mateusz.example.bettingapp.exceptions;

public class DuplicateTeamInMatchException extends RuntimeException {

    public DuplicateTeamInMatchException(String message) {
        super(message);
    }
}
