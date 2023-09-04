package project.monitoring.grafanapractice.common.codeandmessage.specs.product;

import project.monitoring.grafanapractice.common.codeandmessage.ErrorCodeAndMessage;

public enum ProductCodeAndMessage implements ErrorCodeAndMessage {
    PRODUCT_NOT_FOUND(404, "상품을 찾을 수 없습니다.");

    private final int code;
    private final String message;

    ProductCodeAndMessage(
        int code,
        String message
    ) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
