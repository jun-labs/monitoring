package project.monitoring.grafanapractice.test.order.integrationtest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static project.monitoring.grafanapractice.common.auth.AppUser.appUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import project.monitoring.grafanapractice.common.auth.AppUser;
import project.monitoring.grafanapractice.common.exception.DomainException;
import project.monitoring.grafanapractice.core.domain.user.entity.Role;
import project.monitoring.grafanapractice.core.web.order.application.service.OrderService;
import project.monitoring.grafanapractice.core.web.product.exception.ProductNotFoundException;
import project.monitoring.grafanapractice.core.web.user.exception.UserNotFoundException;
import project.monitoring.grafanapractice.test.IntegrationTestBase;

@DisplayName("[IntegrationTest] 주문 통합 테스트")
class OrderCreateIntegrationTest extends IntegrationTestBase {

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("주문이 성공하면 PK가 반환된다.")
    void order_create_test() {
        Long newOrderId = orderService.order(
            appUser,
            1L,
            "스터디 카페 한 달 이용권"
        );

        assertNotNull(newOrderId);
    }

    @Test
    @DisplayName("사용자가 존재하지 않는다면 UserNotFoundException이 발생한다.")
    void user_not_found_case_test() {
        assertThatThrownBy(() -> orderService.order(
            new AppUser(Long.MAX_VALUE, Role.NORMAL),
            1L,
            "스터디 카페 한 달 이용권"
        )).isExactlyInstanceOf(UserNotFoundException.class)
            .isInstanceOf(DomainException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("회원을 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("상품이 존재하지 않는다면 ProductNotFoundException이 발생한다.")
    void product_not_found_case_test() {
        assertThatThrownBy(() -> orderService.order(
            appUser,
            Long.MAX_VALUE,
            "스터디 카페 한 달 이용권"
        )).isExactlyInstanceOf(ProductNotFoundException.class)
            .isInstanceOf(DomainException.class)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("상품을 찾을 수 없습니다.");
    }
}
