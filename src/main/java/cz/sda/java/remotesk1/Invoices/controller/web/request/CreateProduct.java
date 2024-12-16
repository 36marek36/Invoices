package cz.sda.java.remotesk1.Invoices.controller.web.request;

import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode

public class CreateProduct {

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 2, message = "minimalne 2")
    @Max(value = 10,message = "maximalne 10")
    private int price;
}
