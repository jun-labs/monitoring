package project.monitoring.grafanapractice.core.web.order.presentation;

import static project.monitoring.grafanapractice.common.auth.AppUser.appUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.monitoring.grafanapractice.core.web.order.application.service.OrderService;
import project.monitoring.grafanapractice.core.web.order.presentation.request.OrderCreateRequest;
import project.monitoring.grafanapractice.core.web.order.presentation.response.OrderResponse;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
        @RequestBody OrderCreateRequest request
    ) {
        Long newOrderId = orderService.order(
            appUser,
            request.getProductId(),
            request.getTitle()
        );
        return ResponseEntity.ok()
            .body(new OrderResponse(newOrderId));
    }
}
