package nick1.Java.Spring.Web.project.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class PayloadProblems {
    private String message;
    private LocalDateTime time;
}
