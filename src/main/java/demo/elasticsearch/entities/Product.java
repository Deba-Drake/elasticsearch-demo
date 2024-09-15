package demo.elasticsearch.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter @Setter
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product
{
    Integer id;
    String name;
    Long price;
    Boolean isAvailable;
}
