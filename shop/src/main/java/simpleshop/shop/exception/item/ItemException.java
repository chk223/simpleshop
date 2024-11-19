package simpleshop.shop.exception.item;

import simpleshop.shop.exception.CustomException;

public class ItemException extends CustomException {
    public ItemException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }

    public ItemException(String message, String errorCode) {
        super(message, errorCode);
    }
}
