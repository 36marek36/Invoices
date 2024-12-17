package cz.sda.java.remotesk1.Invoices.service;


import cz.sda.java.remotesk1.Invoices.model.Invoice;
import cz.sda.java.remotesk1.Invoices.model.InvoiceItem;
import cz.sda.java.remotesk1.Invoices.model.Product;
import cz.sda.java.remotesk1.Invoices.repository.InvoiceItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Slf4j
@Service

public class InvoiceItemServiceBean implements InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    public InvoiceItemServiceBean(InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }


    @Override
    public InvoiceItem createInvoiceItem(Invoice invoice, Product product, int amount) {
        var invoiceItem = new InvoiceItem(UUID.randomUUID().toString(),invoice,product,amount);
        invoiceItemRepository.save(invoiceItem);
        log.info("Invoice item created");
        return invoiceItem;
    }


    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return StreamSupport.stream(invoiceItemRepository.findAll().spliterator(),false).toList();
    }

    @Override
    public InvoiceItem getInvoiceItemById(String id) {
        return null;
    }

    @Override
    public void deleteInvoiceItemById(String id) {
        if (!invoiceItemRepository.existsById(id)) {
            log.info("Invoice item with id {} not found", id);
            throw new RuntimeException("Invoice item with id " + id + " not found");
        }
        invoiceItemRepository.deleteById(id);
        log.info("Invoice item with id {} deleted", id);
    }

    @Override
    public void updateInvoiceItemById(String id, InvoiceItem item) {

    }
}
