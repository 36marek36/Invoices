package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.controller.rest.request.UpdateProduct;
import cz.sda.java.remotesk1.Invoices.exception.NotFoundException;
import cz.sda.java.remotesk1.Invoices.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class ProductServiceBean implements ProductService {

    private final Map<String, Product> products = new HashMap<>();

    @Override
    public Product addProduct(String name, String price) {
        if (!StringUtils.hasText(name)){
            throw new IllegalArgumentException("Name must not be empty");
        }
        if (!StringUtils.hasText(price)){
            throw new IllegalArgumentException("Price must not be empty");
        }

        var product = new Product(UUID.randomUUID().toString(), name, price);
        if (products.containsKey(product.id())) {
            throw new IllegalArgumentException("Product with id " + product.id() + "already exists");
        }
        products.put(product.id(), product);
        log.info("Product added: {}", product);
        return product;
    }

    @Override
    public void removeProduct(String id) {
        if (!products.containsKey(id)) {
            throw new NotFoundException("Product with id " + id + " does not exists");
        }
        products.remove(id);
        log.info("Product removed: {}", id);

    }

    @Override
    public Product getProduct(String id) {
        if (!products.containsKey(id)) {
            throw new NotFoundException("Product with id " + id + "does not exists");
        }
        return products.get(id);
    }

    @Override
    public Product updateProduct(String id, UpdateProduct updateProduct) {
        if (!products.containsKey(id)) {
            throw new NotFoundException("Client with id " + id + " does not exist");
        }
        var product = products.get(id);
        var builder = Product.builder().id(id);
        if (StringUtils.hasText(updateProduct.name())) {
            builder.name(updateProduct.name());
        } else {
            builder.name(product.name());
        }
        if (StringUtils.hasText(updateProduct.price())) {
            builder.price(updateProduct.price());
        } else {
            builder.price(product.price());
        }
        var updated = builder.build();
        products.put(id, updated);
        log.info("Product updated: {}", updated);
        return updated;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of(products.values().toArray(new Product[0]));
    }
}
