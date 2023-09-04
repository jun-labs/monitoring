package project.monitoring.grafanapractice.core.web.user.application.service;

import org.springframework.stereotype.Service;
import project.monitoring.grafanapractice.core.domain.user.entity.User;
import project.monitoring.grafanapractice.core.domain.user.repository.UserJpaRepository;
import project.monitoring.grafanapractice.core.web.user.application.UserSaveUseCase;

@Service
public class UserSaveService implements UserSaveUseCase {

    private final UserJpaRepository userJpaRepository;

    public UserSaveService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
