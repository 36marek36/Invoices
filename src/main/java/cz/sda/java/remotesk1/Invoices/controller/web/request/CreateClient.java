package cz.sda.java.remotesk1.Invoices.controller.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@EqualsAndHashCode

public class CreateClient {

    @Size(min = 3, max = 10, message = "Name must contain min 3 letters")
//    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Address is required")
    private String address;
}
