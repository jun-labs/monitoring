package project.monitoring.grafanapractice.common.exception.response;

import lombok.Getter;
import project.monitoring.grafanapractice.common.codeandmessage.ErrorCodeAndMessage;

@Getter
public class ErrorResponse {

    private int code;
    private String message;

    private ErrorResponse() {
    }

    public ErrorResponse(ErrorCodeAndMessage codeAndMessage) {
        this.code = codeAndMessage.getCode();
        this.message = codeAndMessage.getMessage();
    }
}
