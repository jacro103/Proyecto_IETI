package org.escuelaing.eci.service;

import org.escuelaing.eci.repository.product.Product;
import org.escuelaing.eci.repository.product.ProductDto;
import org.escuelaing.eci.repository.product.ProductMongoRepository;
import org.escuelaing.eci.service.product.ProductsServiceMongoDb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource(properties = {"spring.data.mongodb.uri=mongodb://localhost/testdb"})
public class ProductServiceMongoDbTest {

    @Mock
    private ProductMongoRepository productMongoRepository;

    @InjectMocks
    private ProductsServiceMongoDb productsServiceMongoDb;

    @Test
    @Order(1)
    public void testFindAllProducts() {
        List<Product> productsListMock = Arrays.asList(
                new Product("1", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488),
                new Product("2", "Skim Milk", "Skim Milk 300ml", "Dairy", 19.526)
        );
        Mockito.when(productMongoRepository.findAll()).thenReturn(productsListMock);
        List<Product> products = productsServiceMongoDb.all();
        Assertions.assertNotNull(products);
        Assertions.assertTrue(products.size() > 0);
        Assertions.assertEquals("Skim Milk 300ml", products.get(1).getDescription());
    }

    @Test
    @Order(2)
    public void testFindProductById() {
        Optional<Product> productMock = Optional.of(new Product("1", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488));
        Mockito.when(productMongoRepository.findById("1")).thenReturn(productMock);
        Optional<Product> product = productsServiceMongoDb.findById("1");
        Assertions.assertNotNull(product);
        Assertions.assertEquals("Whole Milk", product.get().getName());
    }

    @Test
    @Order(3)
    public void testCreateProduct() {
        List<String> tags = Arrays.asList("Milk");
        String urlPhoto = "https://www.eurosupermercados.com/eurosupermercado2020/contenidos/images/ecommerce_productos/7702001041404.jpg";
        ProductDto productFromController = new ProductDto("Whole Milk", "Whole Milk 200ml", "Dairy", tags, 15.488, urlPhoto);
        Product productMock = new Product(productFromController);
        Mockito.when(productMongoRepository.save(productMock)).thenReturn(productMock);
        Product productSaved = productsServiceMongoDb.save(productMock);
        Assertions.assertNotNull(productSaved);
        Assertions.assertEquals("Dairy", productSaved.getCategory());
    }

    @Test
    @Order(4)
    public void testDeleteProductById() {
        Optional<Product> productToDelete = Optional.of(new Product("81dc745f9d7ac326f2fd54f0", "Whole Milk", "Whole Milk 200ml", "Dairy", 15.488));
        Mockito.when(productMongoRepository.findById("81dc745f9d7ac326f2fd54f0")).thenReturn(productToDelete);
        productsServiceMongoDb.deleteById("81dc745f9d7ac326f2fd54f0");
        Mockito.verify(productMongoRepository, Mockito.times(1)).deleteById("81dc745f9d7ac326f2fd54f0");
    }
}
