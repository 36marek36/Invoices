package cz.sda.java.remotesk1.Invoices.controller.web.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode

public class CreateInvoice {

    private String client;
    private String date;
}
