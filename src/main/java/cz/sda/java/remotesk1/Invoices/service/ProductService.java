package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.controller.rest.request.UpdateProduct;
import cz.sda.java.remotesk1.Invoices.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(String name,String price);
    void removeProduct(String id);
    Product getProduct(String id);
    Product updateProduct(String id, UpdateProduct product);
    List<Product> getAllProducts ();
}
