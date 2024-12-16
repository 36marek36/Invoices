package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.controller.rest.request.UpdateProduct;
import cz.sda.java.remotesk1.Invoices.exception.NotFoundException;
import cz.sda.java.remotesk1.Invoices.model.Product;
import cz.sda.java.remotesk1.Invoices.repository.ClientRepository;
import cz.sda.java.remotesk1.Invoices.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ProductServiceBean implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceBean(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product addProduct(String name, int price) {
        if (!StringUtils.hasText(name)){
            throw new IllegalArgumentException("Name must not be empty");
        }
//        if (!StringUtils.hasText(price)){
//            throw new IllegalArgumentException("Price must not be empty");
//        }

        var product = new Product(UUID.randomUUID().toString(), name, price);
        if (productRepository.existsById(product.getId())) {
            throw new IllegalArgumentException("Product with id " + product.getId() + "already exists");
        }
        productRepository.save(product);
        log.info("Product added: {}", product);
        return product;
    }

    @Override
    public void removeProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product with id " + id + " does not exists");
        }
        productRepository.deleteById(id);
        log.info("Product removed: {}", id);

    }

    @Override
    public Product getProduct(String id) {

        return productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " does not exists"));
    }

    @Override
    public Product updateProduct(String id, Product updateProduct) {

        var product = productRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " does not exists"));

        var updated = new Product();
        updated.setId(id);
        if (StringUtils.hasText(updateProduct.getName())) {
            updated.setName(updateProduct.getName());
        } else {
            updated.setName(product.getName());
        }

        if (updateProduct.getPrice() != product.getPrice()) {
            updated.setPrice(updateProduct.getPrice());
        }else {
            updated.setPrice(product.getPrice());
        }

        productRepository.save(updated);
        log.info("Product updated: {}", updated);
        return updated;
    }

    @Override
    public List<Product> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(),false).toList();
    }
}
