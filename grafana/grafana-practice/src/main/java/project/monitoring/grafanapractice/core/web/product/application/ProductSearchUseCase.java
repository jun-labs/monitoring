package project.monitoring.grafanapractice.core.web.product.application;

import project.monitoring.grafanapractice.core.domain.product.entity.Product;

public interface ProductSearchUseCase {

    Product findById(Long productId);
}
