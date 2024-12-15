package cz.sda.java.remotesk1.Invoices.controller.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Builder
@EqualsAndHashCode

public class UpdateProduct {
    @NonNull
    private String id;

    @NotBlank(message = "Name is required")
//    @Size(min = 3,max = 50,message = "Name must contain min 3 letters")
    private String name;

    @NotBlank(message = "Price is required")
    private String price;
}
