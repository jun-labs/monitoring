package project.monitoring.grafanapractice.test.order.snippet;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import java.util.List;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import project.monitoring.grafanapractice.core.web.order.presentation.request.OrderCreateRequest;

public interface OrderSnippet {

    String identifier = "{class-name}/{method-name}";

    FieldDescriptor[] ORDER_REQUEST_SNIPPET = {
        fieldWithPath("productId")
            .description("상품 PK")
            .attributes(key("constraints").value(extractConditions("productId")))
            .optional()
            .type(JsonFieldType.NUMBER),
        fieldWithPath("title")
            .description("주문 제목")
            .attributes(key("constraints").value(extractConditions("title")))
            .optional()
            .type(JsonFieldType.STRING)
    };

    FieldDescriptor[] ORDER_RESPONSE_SNIPPET = {
        fieldWithPath("orderId")
            .description("test")
            .type(JsonFieldType.NUMBER)
    };

    FieldDescriptor[] ORDER_CREATE_ERROR_RESPONSE = {
        fieldWithPath("code").description("에러 코드")
            .type(JsonFieldType.NUMBER),
        fieldWithPath("message").description("에러 메시지")
            .type(JsonFieldType.STRING)
    };

    RestDocumentationResultHandler ORDER_CREATE_RESULT_HANDLER =
        document(
            identifier,
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            requestFields(ORDER_REQUEST_SNIPPET),
            responseFields(ORDER_RESPONSE_SNIPPET)
        );

    RestDocumentationResultHandler ORDER_CREATE_RESULT_HANDLER_EPAGES =
        MockMvcRestDocumentationWrapper.document(
            identifier,
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(
                ResourceSnippetParameters.builder()
                    .tags("Order")
                    .requestFields(ORDER_REQUEST_SNIPPET)
                    .requestSchema(Schema.schema("OrderCreateRequest"))
                    .responseFields(ORDER_RESPONSE_SNIPPET)
                    .build()
            )
        );

    RestDocumentationResultHandler ORDER_CREATE_PRODUCT_NOT_FOUND_HANDLER =
        MockMvcRestDocumentationWrapper.document(
            identifier,
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(
                ResourceSnippetParameters.builder()
                    .tags("Order")
                    .requestFields(ORDER_REQUEST_SNIPPET)
                    .responseFields(ORDER_CREATE_ERROR_RESPONSE)
                    .build()
            )
        );

    static String extractConditions(String key) {
        ConstraintDescriptions constraints =
            new ConstraintDescriptions(OrderCreateRequest.class);
        StringBuilder sb = new StringBuilder();
        List<String> conditions = constraints.descriptionsForProperty(key);

        for (String str : conditions) {
            sb.append(" - ").append(str)
                .append("\n")
                .append("\n");
        }
        return sb.toString();
    }
}
