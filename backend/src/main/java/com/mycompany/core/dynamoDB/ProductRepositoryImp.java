package com.mycompany.core.dynamoDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.mycompany.api.dynamoDB.ProductCatalog;
import com.mycompany.dto.ProductCatalogDTO;


public class ProductRepositoryImp implements ProductRepository {

    public static final String TABLE_NAME = "ProductCatalog";

    AmazonDynamoDB client=null;
    DynamoDBMapper mapper=null;
    DynamoDB dynamoDB = null;
    ProductCatalog item=null;
    private List<ProductCatalog> prodCat;


    public ProductRepositoryImp(){
        
     client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
     mapper = new DynamoDBMapper(client);
     dynamoDB = new DynamoDB(client);

    }
 
    public ProductCatalog createItems(final ProductCatalogDTO dto) {

        item = new ProductCatalog();
        item.setId(dto.getId());
        item.setTitle(dto.getTitle());
        item.setISBN(dto.getISBN());
        // item.setBookAuthors(new HashSet<String>(Arrays.asList("Author 1", "Author
        // 2")));
        item.setBookAuthors(dto.getBookAuthors());
        item.setSomeProp(dto.getSomeProp());
        mapper.save(item);

        return item;
    }

    @Override
    public Optional<ProductCatalog> retrieveItem(final Long id) {
        prodCat=new ArrayList<>();
        item = mapper.load(ProductCatalog.class, id);
        prodCat.add(item);
        return prodCat.stream().filter(e -> e.getId() == id).findFirst();

    }


   
}