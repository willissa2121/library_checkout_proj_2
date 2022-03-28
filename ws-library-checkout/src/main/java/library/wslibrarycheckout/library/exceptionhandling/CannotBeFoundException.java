package library.wslibrarycheckout.library.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CannotBeFoundException extends RuntimeException{

    String message;

    public CannotBeFoundException(String messge) {
        super(messge);
    }
}
