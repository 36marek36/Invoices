package cz.sda.java.remotesk1.Invoices.controller.rest.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CreateInvoiceItem {
    String invoiceId;
    String productId;
    int amount;
}
