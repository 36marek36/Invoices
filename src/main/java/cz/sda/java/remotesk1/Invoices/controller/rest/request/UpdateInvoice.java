package cz.sda.java.remotesk1.Invoices.controller.rest.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class UpdateInvoice {
    String clientId;
    String date;
}
