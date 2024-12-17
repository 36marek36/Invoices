package cz.sda.java.remotesk1.Invoices.controller.rest.request;

import cz.sda.java.remotesk1.Invoices.model.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateInvoice {

    Client client;
    LocalDate date;
}
