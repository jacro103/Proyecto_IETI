package org.escuelaing.eci.service.product;

import org.escuelaing.eci.repository.product.Product;
import org.escuelaing.eci.repository.product.ProductMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceMongoDb implements ProductsService {

    private final ProductMongoRepository productMongoRepository;

    @Autowired
    public ProductsServiceMongoDb(ProductMongoRepository productMongoRepository) {
        this.productMongoRepository = productMongoRepository;
    }

    @Override
    public Product save(Product product) {
        // Guarda el producto en la base de datos de MongoDB y lo retorna
        return productMongoRepository.save(product);
    }

    @Override
    public Optional<Product> findById(String id) {
        // Busca el producto por su id y lo retorna en un Optional
        return productMongoRepository.findById(id);
    }

    @Override
    public List<Product> all() {
        // Retorna la lista de todos los productos de la base de datos
        return productMongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        // Elimina el producto por su id
        productMongoRepository.deleteById(id);
    }

    @Override
    public Product update(Product updatedProduct, String productId) {
        // Busca si el producto con el ID dado existe en la base de datos
        Optional<Product> existingProduct = productMongoRepository.findById(productId);

        if (existingProduct.isPresent()) {
            // Crea un nuevo objeto Product utilizando el ID existente y los datos actualizados
            Product productToUpdate = new Product(
                    productId,  // Mantén el ID original
                    updatedProduct.getName(),
                    updatedProduct.getDescription(),
                    updatedProduct.getCategory(),
                    updatedProduct.getPrice()
            );

            // Guarda el nuevo objeto Product con los datos actualizados y retorna el producto actualizado
            return productMongoRepository.save(productToUpdate);
        }

        // Si no se encuentra el producto, retorna null o puedes lanzar una excepción
        return null;
    }
}
