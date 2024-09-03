package bootcamp.user_crud_api.services;

import bootcamp.user_crud_api.model.User;
import bootcamp.user_crud_api.repository.UserRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailService {

    private final UserRepository userRepository;

    public EmailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

