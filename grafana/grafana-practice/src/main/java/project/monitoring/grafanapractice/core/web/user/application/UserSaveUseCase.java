package project.monitoring.grafanapractice.core.web.user.application;

import project.monitoring.grafanapractice.core.domain.user.entity.User;

public interface UserSaveUseCase {

    User save(User user);
}
