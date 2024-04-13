package nick1.Java.Spring.Web.project.exceptions;

public class ExceptionNotFound extends RuntimeException{
    public ExceptionNotFound(int id){
        super("The Record with id " + id +"was not found");
    }
}
