package nick1.Java.Spring.Web.project.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record EmployeePayload (
        @NotEmpty(message = "Remember to add a username")
        @Size(min = 2, max = 25, message = "The username must be between 2 and 25 characters")
        String username,
        @NotEmpty(message = "Remember to add your first name")
        @Size(min = 2, max = 25, message = "The first must be between 2 and 25 characters")
        String name,

        @NotEmpty(message = "Remember to add your surname")
        @Size(min = 2, max = 25, message = "The surname must be between 2 and 25 characters")
        String surname,

        @NotEmpty(message = "remember to add your email")
        @Email(message = "The given email is not a valid email, please insert a valid one")
        String eMail) {
}
