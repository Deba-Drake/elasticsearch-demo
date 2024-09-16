package demo.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import demo.elasticsearch.entities.Product;
import demo.elasticsearch.util.ElasticSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ElasticSearchService
{
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public String matchAll() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Map> searchResponse= elasticsearchClient.search(s->s.query(supplier.get()),Map.class);
        return searchResponse.hits().hits().toString();
    }

    public List<Product> matchAllProducts() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse= elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
        List<Hit<Product>> listOfHitOfProducts = searchResponse.hits().hits();
        List<Product> productList = new ArrayList<>();
        for (Hit<Product> hit: listOfHitOfProducts)
        {
            productList.add(hit.source());
        }
        return productList;
    }

    public List<Product> matchProductsWithName(String nameValue) throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplierWithName(nameValue);
        SearchResponse<Product> searchResponse= elasticsearchClient.search(s->s.index("products").query(supplier.get()),Product.class);
        List<Hit<Product>> listOfHitOfProducts = searchResponse.hits().hits();
        List<Product> productList = new ArrayList<>();
        for (Hit<Product> hit: listOfHitOfProducts)
        {
            productList.add(hit.source());
        }
        return productList;
    }
}