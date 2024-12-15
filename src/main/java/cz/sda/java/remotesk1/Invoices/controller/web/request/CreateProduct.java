package cz.sda.java.remotesk1.Invoices.controller.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode

public class CreateProduct {

    @NotBlank(message = "Name is required")
//    @Size(min = 3, max = 50)
    private String name;
    @NotBlank(message = "Price is required")
    private String price;
}
