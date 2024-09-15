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

    public Optional<Product> getProduct(Integer id)
    {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product)
    {
        return productRepository.save(product);
    }

    public Product updateProduct(Product exisitngProduct,Product productDetails)
    {
        exisitngProduct.setName(productDetails.getName());
        exisitngProduct.setPrice(productDetails.getPrice());
        exisitngProduct.setIsAvailable(productDetails.getIsAvailable());
        return productRepository.save(exisitngProduct);
    }

    public String deleteProduct(Integer id)
    {
        productRepository.deleteById(id);
        return "Product is Deleted";
    }
}
