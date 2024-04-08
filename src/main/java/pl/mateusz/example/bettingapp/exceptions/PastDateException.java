package pl.mateusz.example.bettingapp.exceptions;

public class PastDateException extends RuntimeException{

    public PastDateException(String message) {
        super(message);
    }
}
