package project.monitoring.grafanapractice.core.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.monitoring.grafanapractice.core.domain.user.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
