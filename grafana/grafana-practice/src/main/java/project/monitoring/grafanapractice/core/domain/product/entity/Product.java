package project.monitoring.grafanapractice.core.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.Getter;
import project.monitoring.grafanapractice.core.domain.common.BaseEntity;

@Getter
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 product 외부 패키지에서 호출하지 말 것.
     */
    protected Product() {
    }

    public Product(
        String name,
        BigDecimal price
    ) {
        this.name = name;
        this.price = price;
    }
}
