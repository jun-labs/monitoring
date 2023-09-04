package project.monitoring.grafanapractice;

import static project.monitoring.grafanapractice.common.auth.AppUser.appUser;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import project.monitoring.grafanapractice.core.domain.product.entity.Product;
import project.monitoring.grafanapractice.core.domain.product.repository.ProductJpaRepository;
import project.monitoring.grafanapractice.core.domain.user.entity.User;
import project.monitoring.grafanapractice.core.domain.user.repository.UserJpaRepository;

@EnableJpaAuditing
@SpringBootApplication
public class GrafanaPracticeApplication {

    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public GrafanaPracticeApplication(
        UserJpaRepository userJpaRepository,
        ProductJpaRepository productJpaRepository
    ) {
        this.userJpaRepository = userJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GrafanaPracticeApplication.class, args);
    }

    @PostConstruct
    public void init() {
        userJpaRepository.save(new User(appUser.getRole()));
        productJpaRepository.save(
            new Product("독서실 한 달 이용권", BigDecimal.valueOf(100_000L))
        );
    }
}
