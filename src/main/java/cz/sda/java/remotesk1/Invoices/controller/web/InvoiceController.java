package cz.sda.java.remotesk1.Invoices.controller.web;

import cz.sda.java.remotesk1.Invoices.controller.web.request.CreateInvoice;
import cz.sda.java.remotesk1.Invoices.model.Invoice;
import cz.sda.java.remotesk1.Invoices.service.ClientService;
import cz.sda.java.remotesk1.Invoices.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final ClientService clientService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, ClientService clientService) {
        this.invoiceService = invoiceService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    String getAllInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("clientList",clientService.getAllClients());
        return "invoices";
    }

    @PostMapping("/add")
    String createInvoice(CreateInvoice createInvoice, Model model) {
        invoiceService.createInvoice(clientService.getClient(createInvoice.getClient()), parseDate(createInvoice.getDate()));
        model.addAttribute("invoice", createInvoice);
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        model.addAttribute("clientList",clientService.getAllClients());
        return "invoices";
    }

    @GetMapping("/delete/{id}")
    String deleteInvoice(@PathVariable String id) {
        invoiceService.deleteInvoice(id);
        return "redirect:/invoices/";
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
