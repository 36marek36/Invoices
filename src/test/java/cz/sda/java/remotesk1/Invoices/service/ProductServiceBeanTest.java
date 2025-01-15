package cz.sda.java.remotesk1.Invoices.service;


import cz.sda.java.remotesk1.Invoices.exception.NotFoundException;
import cz.sda.java.remotesk1.Invoices.model.Product;
import cz.sda.java.remotesk1.Invoices.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProductServiceBeanTest {

    @Autowired
    ProductServiceBean productServiceBean;

    @MockitoBean
    ProductRepository productRepository;

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        ProductServiceBean productServiceBean(ProductRepository productRepository) {
            return new ProductServiceBean(productRepository);
        }
    }

    @Test
    void addProductShouldPass() {
        var uuid = UUID.randomUUID().toString();
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenAnswer(invocationOnMock -> {
                    Product product = invocationOnMock.getArgument(0);
                    product.setId(uuid);
                    return product;
                });

        var product = productServiceBean.addProduct("Kniha", 10);

        assert product != null;
        assert product.getId().equals(uuid);
        assert product.getPrice() == 10;
        assert product.getName().equals("Kniha");
    }

    @Test
    void deleteProductShouldPass() {
        var uuid = UUID.randomUUID().toString();

        Mockito.when(productRepository.existsById(uuid))
                .thenAnswer(invocationOnMock -> true);

        productServiceBean.removeProduct(uuid);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(uuid);

    }

    @Test
    void deleteProductShouldThrowException() {
        var uuid = UUID.randomUUID().toString();
        Mockito.when(productRepository.existsById(Mockito.any(String.class)))
                .thenAnswer(invocationOnMock -> false);

        Exception exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> productServiceBean.removeProduct(uuid)
        );

        Assertions.assertEquals("Product with id " + uuid + " does not exists", exception.getMessage());
    }

    @Test
    void updateProductShouldPass() {
        var uuid = UUID.randomUUID().toString();
        var product= new Product(uuid,"Kniha",25);
        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenAnswer(invocationOnMock -> {
                    return product;
                });

        Mockito.when(productRepository.findById(uuid))
                .thenAnswer(invocationOnMock -> Optional.of(product));

        var updated=productServiceBean.updateProduct(uuid,product);

        assert updated != null;
        assert updated.getId().equals(uuid);
        assert updated.getPrice() == 25;
        assert updated.getName().equals("Kniha");

    }

}
