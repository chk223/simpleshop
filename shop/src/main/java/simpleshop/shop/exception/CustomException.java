package simpleshop.shop.exception;

public class CustomException extends RuntimeException{
    private final String errorCode;

    //기본 예외 처리
    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    //chained 예외 처리를 할 때 cause로 기존 예외 객체를 전달 받아서 던짐(예외처리)
    public CustomException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    //에러 식별용
    public String getErrorCode() {
        return errorCode;
    }
}
