package project.monitoring.grafanapractice.test;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;
import static project.monitoring.grafanapractice.common.auth.AppUser.appUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.restassured.RestAssuredOperationPreprocessorsConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import project.monitoring.grafanapractice.common.annotation.IntegrationTest;
import project.monitoring.grafanapractice.core.domain.product.entity.Product;
import project.monitoring.grafanapractice.core.domain.product.repository.ProductJpaRepository;
import project.monitoring.grafanapractice.core.domain.user.entity.User;
import project.monitoring.grafanapractice.core.domain.user.repository.UserJpaRepository;

@IntegrationTest
public abstract class IntegrationTestBase {

    @LocalServerPort
    protected int port;

    @Autowired
    protected ObjectMapper objectMapper;
    protected RequestSpecification specification;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private RdbInitializationConfiguration databaseInitialization;

    protected IntegrationTestBase() {
        initRestAssureConfiguration();
    }

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        databaseInitialization.truncateAllEntity();
        init();

        OperationPreprocessor operationPreprocessor = modifyUris()
            .removePort();

        RestAssuredOperationPreprocessorsConfigurer restDocumentationFilter =
            documentationConfiguration(restDocumentation)
                .operationPreprocessors()
                .withRequestDefaults(operationPreprocessor, prettyPrint())
                .withResponseDefaults(prettyPrint());

        this.specification = new RequestSpecBuilder()
            .setPort(port)
            .addFilter(restDocumentationFilter)
            .build();
    }

    private void initRestAssureConfiguration() {
        objectMapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        RestAssured.config = new RestAssuredConfig().objectMapperConfig(
            new ObjectMapperConfig().jackson2ObjectMapperFactory((clazz, charset) -> objectMapper)
        );
    }

    @Transactional
    public void init() {
        userJpaRepository.save(new User(appUser.getRole()));
        Product r = productJpaRepository.save(
            new Product("독서실 한 달 이용권", BigDecimal.valueOf(100_000L))
        );
    }
}
