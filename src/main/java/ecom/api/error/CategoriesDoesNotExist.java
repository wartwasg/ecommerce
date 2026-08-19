package ecom.api.error;

public class CategoriesDoesNotExist extends RuntimeException {
    public CategoriesDoesNotExist(String message) {
        super(message);
    }
}
