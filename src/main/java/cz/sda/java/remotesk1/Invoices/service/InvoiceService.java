package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.model.Client;
import cz.sda.java.remotesk1.Invoices.model.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(Client client, LocalDate date);
    Invoice getInvoice(String id);
    List<Invoice> getAllInvoices();
    Invoice updateInvoice(String id, Invoice invoice);
    void deleteInvoice(String id);

}
