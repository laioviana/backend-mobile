package me.laio.backendmobile.service;

import me.laio.backendmobile.domain.User;
import me.laio.backendmobile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }
}
