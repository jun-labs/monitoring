package project.monitoring.grafanapractice.core.web.product.presentation.response;

import lombok.Getter;
import project.monitoring.grafanapractice.core.domain.product.entity.Product;

@Getter
public class ProductResponse {

    private final Long productId;
    private final String name;

    public ProductResponse(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
    }

    @Override
    public String toString() {
        return String.format("productId: %s, name: %s", productId, name);
    }
}
