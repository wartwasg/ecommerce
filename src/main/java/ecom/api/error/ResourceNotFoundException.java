package ecom.api.error;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    String resource;
    String field;
    String fieldName;
    UUID fieldId;
    public ResourceNotFoundException(String resouceName,String field,UUID fieldId,String fieldName,String message) {
        super(message);
        this.resource = resouceName;
        this.field = field;
        this.fieldName = fieldName;
        this.fieldId = fieldId;
    }
}
