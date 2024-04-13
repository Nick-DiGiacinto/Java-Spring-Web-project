package nick1.Java.Spring.Web.project.payloads;

import jakarta.validation.constraints.NotEmpty;

public record DevicePayload(
        @NotEmpty(message = "It is necessary for a device to have a status")
        String status,

        @NotEmpty(message = "It is necessary to specify what type of device it is")
        String type
) {
}