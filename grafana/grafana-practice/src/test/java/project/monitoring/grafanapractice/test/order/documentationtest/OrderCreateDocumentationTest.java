package project.monitoring.grafanapractice.test.order.documentationtest;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static project.monitoring.grafanapractice.test.order.snippet.OrderSnippet.ORDER_CREATE_PRODUCT_NOT_FOUND_HANDLER;
import static project.monitoring.grafanapractice.test.order.snippet.OrderSnippet.ORDER_CREATE_RESULT_HANDLER;
import static project.monitoring.grafanapractice.test.order.snippet.OrderSnippet.ORDER_CREATE_RESULT_HANDLER_EPAGES;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.monitoring.grafanapractice.core.web.order.presentation.request.OrderCreateRequest;
import project.monitoring.grafanapractice.test.IntegrationTestBase;

@DisplayName("[DocumentationTest] 주문 생성 API 테스트")
class OrderCreateDocumentationTest extends IntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("주문이 성공하면 200 OK가 반환된다.")
    void order_create_test() throws Exception {
        OrderCreateRequest request =
            new OrderCreateRequest(1L, "독서실 한 달 정기 이용권");

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isOk())
            .andDo(ORDER_CREATE_RESULT_HANDLER)
            .andDo(ORDER_CREATE_RESULT_HANDLER_EPAGES);
    }

    @Test
    @DisplayName("상품이 존재하지 않는다면 404 NOT FOUND가 반환된다.")
    void order_create_product_not_found_test() throws Exception {
        OrderCreateRequest request =
            new OrderCreateRequest(10000L, "독서실 한 달 정기 이용권");

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            )
            .andExpect(status().isNotFound())
            .andDo(ORDER_CREATE_PRODUCT_NOT_FOUND_HANDLER);
    }
}
