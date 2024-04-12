package pl.mateusz.example.bettingapp.services;

import pl.mateusz.example.bettingapp.dto.UserDto;
import pl.mateusz.example.bettingapp.entities.User;
import pl.mateusz.example.bettingapp.repositories.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
