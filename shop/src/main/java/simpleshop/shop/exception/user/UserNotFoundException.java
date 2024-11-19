package simpleshop.shop.exception.user;

import simpleshop.shop.exception.CustomException;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(message, "USER_NOT_FOUND");
    }
}
