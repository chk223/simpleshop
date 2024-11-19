package simpleshop.shop.exception.user;

import simpleshop.shop.exception.CustomException;

public class UserAlreadyExistException extends CustomException {
    public UserAlreadyExistException(String message) {
        super(message, "USER_ALREADY_EXISTS");
    }
}
