package library.wslibrarycheckout.library.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
