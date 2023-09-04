package project.monitoring.grafanapractice.core.domain.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import project.monitoring.grafanapractice.core.domain.common.BaseEntity;

@Getter
@Entity(name = "order_line")
public class OrderLine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "price")
    private BigDecimal price;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 order 외부 패키지에서 호출하지 말 것.
     */
    protected OrderLine() {
    }

    public OrderLine(
        Long productId,
        BigDecimal price
    ) {
        this.productId = productId;
        this.price = price;
    }

    public void add(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof OrderLine orderLine)) {
            return false;
        }
        return getId().equals(orderLine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
