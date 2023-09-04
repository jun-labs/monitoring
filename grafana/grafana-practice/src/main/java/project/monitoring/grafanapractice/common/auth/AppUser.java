package project.monitoring.grafanapractice.common.auth;

import java.util.Objects;
import lombok.Getter;
import project.monitoring.grafanapractice.core.domain.user.entity.Role;

@Getter
public class AppUser {

    public static final AppUser appUser = new AppUser(1L, Role.NORMAL);

    private final Long userId;
    private final Role role;

    public AppUser(
        Long userId,
        Role role
    ) {
        this.userId = userId;
        this.role = role;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AppUser appUser)) {
            return false;
        }
        return getUserId().equals(appUser.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
