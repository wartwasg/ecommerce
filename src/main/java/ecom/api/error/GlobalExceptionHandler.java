package ecom.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> result = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err->{
           String fieldName = ((FieldError) err).getField();
           String message = err.getDefaultMessage();
           result.put(fieldName,message);
        });
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundException(ResourceNotFoundException e){
        return new  ResponseEntity<>(new APIResponse(e.getMessage(),"failed"),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ApiCustomException.class)
    public ResponseEntity<APIResponse> apiCustomException(ApiCustomException e){
        return new  ResponseEntity<>(new APIResponse(e.getMessage(),"failed"),HttpStatus.CONFLICT);
    }
    @ExceptionHandler(CategoriesDoesNotExist.class)
    public ResponseEntity<APIResponse> categoriesDoesNotExist(CategoriesDoesNotExist e){
        return new  ResponseEntity<>(new APIResponse(e.getMessage(),"failed"),HttpStatus.NOT_FOUND);
    }
}
