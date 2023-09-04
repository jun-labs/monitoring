package project.monitoring.grafanapractice.core.web.order.application;

import project.monitoring.grafanapractice.common.auth.AppUser;

public interface OrderCreateUseCase {

    Long order(AppUser appUser, Long productId, String title);
}
