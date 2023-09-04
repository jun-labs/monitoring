package project.monitoring.grafanapractice.test.product.snippet;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ParameterDescriptorWithType;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.RequestDocumentation;

public interface ProductSnippet {

    String identifier = "{class-name}/{method-name}";

    ParameterDescriptorWithType PRODUCT_SEARCH_PATH_SNIPPET =
        parameterWithName("productId")
            .description("상품 PK");

    FieldDescriptor[] PRODUCT_SEARCH_RESPONSE_SNIPPET = {
        fieldWithPath("productId")
            .description("상품 PK")
            .type(JsonFieldType.NUMBER),
        fieldWithPath("name")
            .description("상품명")
            .type(JsonFieldType.STRING)
    };

    FieldDescriptor[] PRODUCT_SEARCH_ERROR_RESPONSE = {
        fieldWithPath("code").description("에러 코드")
            .type(JsonFieldType.NUMBER),
        fieldWithPath("message").description("에러 메시지")
            .type(JsonFieldType.STRING)
    };

    RestDocumentationResultHandler PRODUCT_SEARCH_HANDLER =
        document(
            identifier,
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            pathParameters(
                RequestDocumentation.parameterWithName("productId")
                    .description("상품 PK")
            ),
            responseFields(
                fieldWithPath("productId").description("상품 PK")
                    .type(JsonFieldType.NUMBER),
                fieldWithPath("name").description("상품명")
                    .type(JsonFieldType.STRING)
            )
        );

    RestDocumentationResultHandler PRODUCT_SEARCH_HANDLER_EPAGES =
        MockMvcRestDocumentationWrapper.document(
            identifier,
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(
                ResourceSnippetParameters.builder()
                    .tags("Product")
                    .pathParameters(PRODUCT_SEARCH_PATH_SNIPPET)
                    .requestSchema(Schema.schema("Product Search By Id"))
                    .responseFields(PRODUCT_SEARCH_RESPONSE_SNIPPET)
                    .build()
            )
        );

    RestDocumentationResultHandler PRODUCT_SEARCH_NOT_FOUND_HANDLER =
        MockMvcRestDocumentationWrapper.document(
            identifier,
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(
                ResourceSnippetParameters.builder()
                    .tags("Product")
                    .pathParameters(PRODUCT_SEARCH_PATH_SNIPPET)
                    .responseFields(PRODUCT_SEARCH_ERROR_RESPONSE)
                    .build()
            )
        );
}
