package cz.sda.java.remotesk1.Invoices.repository;

import cz.sda.java.remotesk1.Invoices.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {
}
