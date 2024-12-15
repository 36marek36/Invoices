package cz.sda.java.remotesk1.Invoices.controller.rest;

import cz.sda.java.remotesk1.Invoices.controller.rest.request.CreateProduct;
import cz.sda.java.remotesk1.Invoices.controller.rest.request.UpdateProduct;
import cz.sda.java.remotesk1.Invoices.exception.NotFoundException;
import cz.sda.java.remotesk1.Invoices.model.Product;
import cz.sda.java.remotesk1.Invoices.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest/products")
public class ProductApi {

    private final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    ResponseEntity<Product> addProduct(@RequestBody CreateProduct product){
        Product created = productService.addProduct(product.name(), product.price());
        return ResponseEntity.created(URI.create("/products/"+created.getId())).body(created);
    }
    @GetMapping("/")
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    Product getProduct(@NonNull @PathVariable("id") String id) {
        return productService.getProduct(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> removeProduct(@PathVariable("id") String id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody UpdateProduct product) {
        var updated = productService.updateProduct(id, new Product(id, product.name(), product.price()));
        return ResponseEntity.ok(updated);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        log.debug("Product not found");
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<String> handleNotFoundArgumentException(NotFoundException e){
        log.error("Product not found");
        return ResponseEntity.notFound().build();
    }
}
