package cz.sda.java.remotesk1.Invoices.controller.rest;

import cz.sda.java.remotesk1.Invoices.model.Product;
import cz.sda.java.remotesk1.Invoices.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductApi.class)
public class ProductApiTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void addProductShouldReturnCreatedProduct() throws Exception {
        var product = new Product(UUID.randomUUID().toString(), "Kniha", 20);

        Mockito.when(productService.addProduct(Mockito.any(String.class), Mockito.any(Integer.class))).thenReturn(product);

        mvc.perform(post("/rest/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Kniha\",\"price\":20}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/products/" + product.getId()))
                .andExpect(jsonPath("$.name").value("Kniha"))
                .andExpect(jsonPath("$.price").value(20));
    }

    @Test
    void getAllProductsShouldReturnAllProducts() throws Exception {
        var product = new Product(UUID.randomUUID().toString(), "Kniha", 20);

        Mockito.when(productService.getAllProducts()).thenReturn(List.of(product));

        mvc.perform(get("/rest/products/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Kniha"))
                .andExpect(jsonPath("$[0].price").value(20));
    }

    @Test
    void getProductShouldReturnProduct() throws Exception {
        var product = new Product(UUID.randomUUID().toString(), "Kniha", 20);
        Mockito.when(productService.getProduct(Mockito.any(String.class))).thenReturn(product);

        mvc.perform(get("/rest/products/" + product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kniha"))
                .andExpect(jsonPath("$.price").value(20));
    }

    @Test
    void removeProductShouldReturnNoContent() throws Exception {
        var productId = UUID.randomUUID().toString();
        Mockito.doNothing().when(productService).removeProduct(productId);

        mvc.perform(delete("/rest/products/{id}", productId))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateProductShouldReturnUpdatedProduct() throws Exception {
        var productId = UUID.randomUUID().toString();
        var product = new Product(productId, "Kniha", 20);

        Mockito.when(productService.updateProduct(Mockito.any(String.class), Mockito.any(Product.class))).thenReturn(product);

        mvc.perform(patch("/rest/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Kniha\",\"price\":20}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kniha"))
                .andExpect(jsonPath("$.price").value(20));

    }
}
