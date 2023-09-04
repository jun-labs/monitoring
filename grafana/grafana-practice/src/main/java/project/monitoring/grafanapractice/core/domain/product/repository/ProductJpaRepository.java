package project.monitoring.grafanapractice.core.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.monitoring.grafanapractice.core.domain.product.entity.Product;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

}
