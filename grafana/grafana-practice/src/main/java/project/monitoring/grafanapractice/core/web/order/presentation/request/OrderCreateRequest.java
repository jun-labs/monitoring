package project.monitoring.grafanapractice.core.web.order.presentation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;

@Getter
public class OrderCreateRequest {

    @NotNull
    @Positive
    private Long productId;

    @NotNull
    @NotBlank
    @Length(max = 30)
    private String title;

    private OrderCreateRequest() {
    }

    public OrderCreateRequest(
        Long productId,
        String title
    ) {
        this.productId = productId;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("productId: %s, title: %s", productId, title);
    }
}
