package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.model.Invoice;
import cz.sda.java.remotesk1.Invoices.model.InvoiceItem;
import cz.sda.java.remotesk1.Invoices.model.Product;

import java.util.List;

public interface InvoiceItemService {
    InvoiceItem createInvoiceItem(Invoice invoice, Product product, int amount);
    List<InvoiceItem> getAllInvoiceItems();
    InvoiceItem getInvoiceItemById(String id);
    void deleteInvoiceItemById(String id);
    void updateInvoiceItemById(String id, InvoiceItem item);

}
