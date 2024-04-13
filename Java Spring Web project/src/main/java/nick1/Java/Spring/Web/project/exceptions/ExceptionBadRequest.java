package nick1.Java.Spring.Web.project.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;
import java.util.Collection;
import java.util.List;

@Getter
public class ExceptionBadRequest extends RuntimeException{

    public List<ObjectError> errorsList;
    public ExceptionBadRequest(String message){
        super(message);
    }

    public ExceptionBadRequest(List<ObjectError> errorsList){
        super("The payload-validation contains some type of error");
        this.errorsList = errorsList;
    }
}
