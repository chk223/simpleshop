package simpleshop.shop.exception.user;

import simpleshop.shop.exception.CustomException;

public class UserException extends CustomException {
    public UserException(String message, String errorCode) {
        super(message, errorCode);
    }

    public UserException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }
}
