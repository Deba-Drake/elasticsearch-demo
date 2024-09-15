package demo.elasticsearch.controllers;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import demo.elasticsearch.entities.Product;
import demo.elasticsearch.service.ElasticSearchService;
import demo.elasticsearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
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

    @GetMapping("/product/{id}")
    public Product singleProduct(@PathVariable Integer id)
    {
        return productService.getProduct(id).orElseThrow(() -> new RuntimeException("Product " + id + " not found."));
    }

    @GetMapping("/product/match-all")
    public String matchAll() throws IOException {
        return elasticSearchService.matchALLService();
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
        Product exisitngProduct = singleProduct(id);
        return productService.updateProduct(exisitngProduct,productDetails);
    }

    @DeleteMapping("/product/{id}")
    public String removeProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}