package cz.sda.java.remotesk1.Invoices.controller.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderItem {
    private String productId;
    private Integer amount;
}