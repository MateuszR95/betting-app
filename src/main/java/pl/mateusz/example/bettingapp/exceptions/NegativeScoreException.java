package pl.mateusz.example.bettingapp.exceptions;

public class NegativeScoreException extends RuntimeException{

    public NegativeScoreException(String message) {
        super(message);
    }
}
