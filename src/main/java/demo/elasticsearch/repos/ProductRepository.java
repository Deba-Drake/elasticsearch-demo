package demo.elasticsearch.repos;

import demo.elasticsearch.entities.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product,Integer> {}