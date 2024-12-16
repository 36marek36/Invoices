package cz.sda.java.remotesk1.Invoices.controller.web.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@EqualsAndHashCode

public class UpdateClient {

    @NonNull
    private String id;

//    @Pattern(regexp =)
    @Size(min = 3, max = 50, message = "Name must contain min 3 letters")
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;

}
