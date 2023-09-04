package project.monitoring.grafanapractice.core.web.order.presentation.response;

import lombok.Getter;

@Getter
public class OrderResponse {

    private final Long orderId;

    public OrderResponse(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return String.format("orderId: %s", orderId);
    }
}
