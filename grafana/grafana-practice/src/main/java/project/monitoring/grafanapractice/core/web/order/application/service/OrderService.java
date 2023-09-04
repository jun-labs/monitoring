package project.monitoring.grafanapractice.core.web.order.application.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.monitoring.grafanapractice.common.auth.AppUser;
import project.monitoring.grafanapractice.core.domain.order.entity.Order;
import project.monitoring.grafanapractice.core.domain.order.entity.OrderLine;
import project.monitoring.grafanapractice.core.domain.order.repository.OrderJpaRepository;
import project.monitoring.grafanapractice.core.domain.product.entity.Product;
import project.monitoring.grafanapractice.core.domain.product.repository.ProductJpaRepository;
import project.monitoring.grafanapractice.core.domain.user.entity.User;
import project.monitoring.grafanapractice.core.domain.user.repository.UserJpaRepository;
import project.monitoring.grafanapractice.core.web.order.application.OrderCreateUseCase;
import project.monitoring.grafanapractice.core.web.product.exception.ProductNotFoundException;
import project.monitoring.grafanapractice.core.web.user.exception.UserNotFoundException;

@Service
public class OrderService implements OrderCreateUseCase {

    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final OrderJpaRepository orderJpaRepository;

    public OrderService(
        UserJpaRepository userJpaRepository,
        ProductJpaRepository productJpaRepository,
        OrderJpaRepository orderJpaRepository
    ) {
        this.userJpaRepository = userJpaRepository;
        this.productJpaRepository = productJpaRepository;
        this.orderJpaRepository = orderJpaRepository;
    }

    @Transactional
    public Long order(
        AppUser appUser,
        Long productId,
        String title
    ) {
        User findUser = userJpaRepository.findById(appUser.getUserId())
            .orElseThrow(UserNotFoundException::new);

        Product product = productJpaRepository.findById(productId)
            .orElseThrow(ProductNotFoundException::new);

        Order newOrder = orderJpaRepository.save(
            createOrder(findUser.getId(), title, product)
        );
        return newOrder.getId();
    }

    private Order createOrder(
        Long userId,
        String title,
        Product product
    ) {
        Order order = new Order(userId, title);

        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(new OrderLine(product.getId(), product.getPrice()));

        order.add(orderLines);
        return order;
    }
}
