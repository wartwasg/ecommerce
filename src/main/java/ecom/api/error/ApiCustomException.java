package ecom.api.error;

public class ApiCustomException extends RuntimeException{
    private String message;

    public ApiCustomException(String message) {
        super(message);
        this.message = message;
    }
}
