package demo.elasticsearch.service;

import demo.elasticsearch.entities.Product;
import demo.elasticsearch.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product createProduct(Product product)
    {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product productDetails) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            if (productDetails.getName() != null) existingProduct.setName(productDetails.getName());
            if (productDetails.getPrice() != null) existingProduct.setPrice(productDetails.getPrice());
            if (productDetails.getIsAvailable() != null) existingProduct.setIsAvailable(productDetails.getIsAvailable());

            return productRepository.save(existingProduct);
        }
        else throw new RuntimeException("Product not found with ID: " + id); // Replace with a more appropriate exception if needed
    }

    public String deleteProduct(Integer id)
    {
        productRepository.deleteById(id);
        return "Product is Deleted";
    }
}
