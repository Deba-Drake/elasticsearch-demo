package demo.elasticsearch.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter @Setter
@Document(indexName = "products")

public class Product
{
    Integer id;
    String name;
    Long price;
    Boolean isAvailable;
}
