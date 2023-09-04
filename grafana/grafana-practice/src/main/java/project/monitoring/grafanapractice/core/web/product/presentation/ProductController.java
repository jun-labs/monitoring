package project.monitoring.grafanapractice.core.web.product.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.monitoring.grafanapractice.core.domain.product.entity.Product;
import project.monitoring.grafanapractice.core.web.product.application.ProductSearchUseCase;
import project.monitoring.grafanapractice.core.web.product.presentation.response.ProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductSearchUseCase productSearchUseCase;

    public ProductController(ProductSearchUseCase productSearchUseCase) {
        this.productSearchUseCase = productSearchUseCase;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> searchProductById(
        @PathVariable Long productId
    ) {
        Product findProduct = productSearchUseCase.findById(productId);
        ProductResponse payload = new ProductResponse(findProduct);
        return ResponseEntity.ok(payload);
    }
}
