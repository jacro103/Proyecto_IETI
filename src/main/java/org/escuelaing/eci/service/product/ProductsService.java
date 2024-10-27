package org.escuelaing.eci.service.product;

import org.escuelaing.eci.repository.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsService {

    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> all();

    void deleteById(String id);

    Product update(Product Product, String ProductId);
}
