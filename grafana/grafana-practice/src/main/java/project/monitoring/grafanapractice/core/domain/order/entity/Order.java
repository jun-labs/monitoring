package project.monitoring.grafanapractice.core.domain.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import project.monitoring.grafanapractice.core.domain.common.BaseEntity;

@Getter
@Entity(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title")
    private String title;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderLine> orderLines = new ArrayList<>();

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 order 외부 패키지에서 호출하지 말 것.
     */
    protected Order() {
    }

    public Order(
        Long userId,
        String title
    ) {
        this.userId = userId;
        this.title = title;
    }

    public void add(List<OrderLine> orderLines) {
        orderLines
            .forEach(orderLine -> orderLine.add(this));
        this.orderLines = orderLines;
        this.totalPrice = orderLines.stream()
            .map(OrderLine::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Order order)) {
            return false;
        }
        return getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
