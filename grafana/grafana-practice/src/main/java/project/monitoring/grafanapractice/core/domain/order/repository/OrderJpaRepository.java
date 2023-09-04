package project.monitoring.grafanapractice.core.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.monitoring.grafanapractice.core.domain.order.entity.Order;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
