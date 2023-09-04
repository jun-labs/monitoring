package project.monitoring.grafanapractice.core.web.product.application.service;

import org.springframework.stereotype.Service;
import project.monitoring.grafanapractice.core.domain.product.entity.Product;
import project.monitoring.grafanapractice.core.domain.product.repository.ProductJpaRepository;
import project.monitoring.grafanapractice.core.web.product.application.ProductSearchUseCase;
import project.monitoring.grafanapractice.core.web.product.exception.ProductNotFoundException;

@Service
public class ProductService implements ProductSearchUseCase {

    private final ProductJpaRepository productJpaRepository;

    public ProductService(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Product findById(Long productId) {
        return productJpaRepository.findById(productId)
            .orElseThrow(ProductNotFoundException::new);
    }
}
