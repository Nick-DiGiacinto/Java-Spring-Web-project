package nick1.Java.Spring.Web.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsManager {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PayloadProblems handleBadRequest(ExceptionBadRequest ex){
        if (ex.getErrorsList() != null){
            String message = ex.getErrorsList().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return new PayloadProblems(message, LocalDateTime.now());
        }else {return new PayloadProblems(ex.getMessage(), LocalDateTime.now());}


    }

    @ExceptionHandler(ExceptionNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public PayloadProblems handleNotFound(ExceptionNotFound ex){
        return new PayloadProblems(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public PayloadProblems handleGenericErrors(Exception ex){
        ex.printStackTrace();
        return new PayloadProblems("There was a problem from the server side", LocalDateTime.now());
    }
}
