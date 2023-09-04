package project.monitoring.grafanapractice.common.codeandmessage.specs.user;

import project.monitoring.grafanapractice.common.codeandmessage.ErrorCodeAndMessage;

public enum UserCodeAndMessage implements ErrorCodeAndMessage {
    USER_NOT_FOUND(404, "회원을 찾을 수 없습니다.");

    private final int code;
    private final String message;

    UserCodeAndMessage(
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
