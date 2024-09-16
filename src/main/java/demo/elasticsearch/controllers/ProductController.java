package demo.elasticsearch.controllers;

import demo.elasticsearch.entities.Product;
import demo.elasticsearch.service.ElasticSearchService;
import demo.elasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController @RequestMapping("/apis")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/all-products")
    public Iterable<Product> allProducts()
    {
        return productService.getAllProducts();
    }

    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty() || product.getPrice() <= 0.00)
        {
            if (product.getPrice() == null || Objects.requireNonNull(product.getName()).trim().isEmpty()) throw new RuntimeException("The Product Name cannot be Empty");
            else if (product.getPrice() <= 0.00) throw new RuntimeException("The Product Price should be more than Zero");
            else throw new RuntimeException("Error occurred");
        }
        return productService.createProduct(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product productDetails)
    {
        return productService.updateProduct(id,productDetails);
    }

    @DeleteMapping("/product/{id}")
    public String removeProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/match-all")
    public String matchAll() throws IOException {
        return elasticSearchService.matchAll();
    }

    @GetMapping("/product/match-all")
    public List<Product> matchAllProducts() throws IOException {
        return elasticSearchService.matchAllProducts();
    }

    @GetMapping("/product/match-all/{nameValue}")
    public List<Product> matchAllProductsWithName(@PathVariable String nameValue) throws IOException {
        return elasticSearchService.matchProductsWithName(nameValue);
    }
}