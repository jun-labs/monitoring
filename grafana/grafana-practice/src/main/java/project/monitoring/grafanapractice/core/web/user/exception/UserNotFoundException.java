package project.monitoring.grafanapractice.core.web.user.exception;

import project.monitoring.grafanapractice.common.codeandmessage.specs.user.UserCodeAndMessage;
import project.monitoring.grafanapractice.common.exception.DomainException;

public class UserNotFoundException extends DomainException {

    public UserNotFoundException() {
        super(UserCodeAndMessage.USER_NOT_FOUND);
    }
}
