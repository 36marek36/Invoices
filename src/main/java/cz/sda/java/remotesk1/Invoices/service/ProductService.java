package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.model.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(String name,int price);
    void removeProduct(String id);
    Product getProduct(String id);
    Product updateProduct(String id, Product product);
    List<Product> getAllProducts ();
}
