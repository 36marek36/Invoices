package cz.sda.java.remotesk1.Invoices.controller.rest;

import cz.sda.java.remotesk1.Invoices.controller.rest.request.CreateInvoice;
import cz.sda.java.remotesk1.Invoices.controller.rest.request.UpdateInvoice;
import cz.sda.java.remotesk1.Invoices.model.Invoice;
import cz.sda.java.remotesk1.Invoices.service.ClientService;
import cz.sda.java.remotesk1.Invoices.service.InvoiceService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/invoices")
public class InvoiceApi {

    private final InvoiceService invoiceService;
    private final ClientService clientService;

    @Autowired
    public InvoiceApi(InvoiceService invoiceService, ClientService clientService) {
        this.invoiceService = invoiceService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    List<Invoice> getInvoices() {
        return invoiceService.getAllInvoices();
    }

    @PostMapping("/")
    ResponseEntity<Invoice> createInvoice(@RequestBody CreateInvoice invoice) {
        Invoice created = invoiceService.createInvoice(invoice.getClient(), invoice.getDate());
//        return ResponseEntity.created(URI.create("/invoices/" + created.getId())).body(created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    Invoice getInvoice (@PathVariable("id") String id) {
        log.info("Get invoice with id {}", id);
        return invoiceService.getInvoice(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") String id) {
        log.info("Delete invoice with id {}", id);
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    ResponseEntity<Invoice> updateInvoice(@PathVariable("id") String id, @RequestBody UpdateInvoice invoice) {
        log.info("Update invoice with id {}", id);
        invoiceService.updateInvoice(id, clientService.getClient(invoice.getClientId()),parseDate(invoice.getDate()));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(dateString, formatter);
            return date;
        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
            return LocalDate.now();
        }
    }

}
