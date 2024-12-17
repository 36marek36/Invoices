package cz.sda.java.remotesk1.Invoices.controller.rest;

import cz.sda.java.remotesk1.Invoices.controller.rest.request.CreateInvoiceItem;
import cz.sda.java.remotesk1.Invoices.model.InvoiceItem;
import cz.sda.java.remotesk1.Invoices.service.InvoiceItemService;
import cz.sda.java.remotesk1.Invoices.service.InvoiceService;
import cz.sda.java.remotesk1.Invoices.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/invoices-item/")

public class InvoiceItemApi {
    private final InvoiceItemService invoiceItemService;
    private final InvoiceService invoiceService;
    private final ProductService productService;

    public InvoiceItemApi(InvoiceItemService invoiceItemService, InvoiceService invoiceService, ProductService productService) {
        this.invoiceItemService = invoiceItemService;
        this.invoiceService = invoiceService;
        this.productService = productService;
    }

    @GetMapping("/")
    List<InvoiceItem> getInvoiceItems() {
        log.info("getInvoiceItems");
        return invoiceItemService.getAllInvoiceItems();
    }

    @PostMapping("/")
    ResponseEntity<InvoiceItem> createInvoiceItem(@RequestBody CreateInvoiceItem invoiceItem) {
        InvoiceItem created = invoiceItemService.createInvoiceItem(invoiceService.getInvoice(invoiceItem.getInvoiceId()), productService.getProduct(invoiceItem.getProductId()), invoiceItem.getAmount());
        log.info("createInvoiceItem: {}", created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<InvoiceItem> deleteInvoiceItem(@PathVariable("id") String id) {
        log.info("deleteInvoiceItem: {}", id);
        invoiceItemService.deleteInvoiceItemById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
