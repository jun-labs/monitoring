package project.monitoring.grafanapractice.test.product.documentationtest;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.monitoring.grafanapractice.test.product.snippet.ProductSnippet.PRODUCT_SEARCH_HANDLER;
import static project.monitoring.grafanapractice.test.product.snippet.ProductSnippet.PRODUCT_SEARCH_HANDLER_EPAGES;
import static project.monitoring.grafanapractice.test.product.snippet.ProductSnippet.PRODUCT_SEARCH_NOT_FOUND_HANDLER;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import project.monitoring.grafanapractice.test.IntegrationTestBase;

@ActiveProfiles("test")
@DisplayName("[DocumentationTest] 상품 조회 API 테스트")
class ProductSearchDocumentationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("상품 조회가 성공하면 200 OK가 반환된다.")
    void product_search_by_id_test() throws Exception {
        mockMvc.perform(get("/api/products/{productId}", 1))
            .andExpect(status().isOk())
            .andDo(PRODUCT_SEARCH_HANDLER)
            .andDo(PRODUCT_SEARCH_HANDLER_EPAGES);
    }

    @Test
    @DisplayName("상품이 존재하지 않는다면 404 NOT FOUND가 반환된다.")
    void product_search_not_found_test() throws Exception {
        mockMvc.perform(get("/api/products/{productId}", 1_000_000))
            .andExpect(status().isNotFound())
            .andDo(PRODUCT_SEARCH_NOT_FOUND_HANDLER);
    }
}
