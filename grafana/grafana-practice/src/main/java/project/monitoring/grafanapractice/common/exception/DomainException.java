package project.monitoring.grafanapractice.common.exception;

import lombok.Getter;
import project.monitoring.grafanapractice.common.codeandmessage.ErrorCodeAndMessage;

@Getter
public class DomainException extends RuntimeException {

    private ErrorCodeAndMessage errorCodeAndMessage;

    protected DomainException(ErrorCodeAndMessage errorCodeAndMessage) {
        super(errorCodeAndMessage.getMessage());
        this.errorCodeAndMessage = errorCodeAndMessage;
    }

    public int getErrorCode() {
        return errorCodeAndMessage.getCode();
    }

    public String getErrorMessage() {
        return errorCodeAndMessage.getMessage();
    }
}
