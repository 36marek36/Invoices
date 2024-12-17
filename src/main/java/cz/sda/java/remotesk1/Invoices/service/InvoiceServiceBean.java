package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.exception.NotFoundException;
import cz.sda.java.remotesk1.Invoices.model.Client;
import cz.sda.java.remotesk1.Invoices.model.Invoice;
import cz.sda.java.remotesk1.Invoices.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class InvoiceServiceBean implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceBean(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public Invoice createInvoice(Client client, LocalDate date) {
        var invoice = new Invoice(UUID.randomUUID().toString(), client, date);
        invoiceRepository.save(invoice);
        log.info("Invoice created: " + invoice);
        return invoice;
    }

    @Override
    public Invoice getInvoice(String id) {
        return invoiceRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice not found"));
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return StreamSupport.stream(invoiceRepository.findAll().spliterator(),false).toList();
    }

    @Override
    public void deleteInvoice(String id) {
        if (!invoiceRepository.existsById(id)) {
            throw new NotFoundException("Invoice with id " + id + " does not exists");
        }
        invoiceRepository.deleteById(id);
        log.info("Invoice removed: {}", id);
    }

    @Override
    public void updateInvoice(String id, Client client, LocalDate date) {
        if (!invoiceRepository.existsById(id)) {
            throw new NotFoundException("Invoice with id " + id + " does not exists");
        }
        var invoice=invoiceRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " does not exists"));
        if (client != null) {
            invoice.setClient(client);
        }
        if (date != null) {
            invoice.setDate(date);
        }
        invoiceRepository.save(invoice);
        log.info("Invoice updated: " + invoice);

    }
}
