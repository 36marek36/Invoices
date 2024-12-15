package cz.sda.java.remotesk1.Invoices.repository;

import cz.sda.java.remotesk1.Invoices.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
}
