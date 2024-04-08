package pl.mateusz.example.bettingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.example.bettingapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
