package me.laio.backendmobile.repository;

import me.laio.backendmobile.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLogin(String login);
}
