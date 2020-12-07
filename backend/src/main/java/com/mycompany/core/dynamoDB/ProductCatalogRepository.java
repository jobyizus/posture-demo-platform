package com.mycompany.core.dynamoDB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.mycompany.api.dynamoDB.ProductCatalog;
import com.mycompany.dto.ProductCatalogDTO;

public class ProductCatalogRepository {

    AmazonDynamoDB client=null;
    DynamoDBMapper mapper=null;
    DynamoDB dynamoDB = null;

    ProductCatalog item=null;
    public static final String TABLE_NAME = "ProductCatalog";


    public ProductCatalogRepository(){
        
     client = AmazonDynamoDBClientBuilder.standard().build();
     mapper = new DynamoDBMapper(client);
     dynamoDB = new DynamoDB(client);



    }

 
    public ProductCatalog createItems(ProductCatalogDTO dto) {

         item = new ProductCatalog();
        item.setId(102);
        item.setTitle("Book 102 Title");
        item.setISBN("222-2222222222");
        item.setBookAuthors(new HashSet<String>(Arrays.asList("Author 1", "Author 2")));
        item.setSomeProp("Test");        
        mapper.save(item);

        return item;

    } 
    
    public  ProductCatalog retrieveItem(Long id) {

        item = new ProductCatalog();

        item.setId(102);
DynamoDBQueryExpression<ProductCatalog> queryExpression = new DynamoDBQueryExpression<ProductCatalog>()
    .withHashKeyValues(item);

        List<ProductCatalog> itemList = mapper.query(ProductCatalog.class, queryExpression);

    return itemList.get(0);

    }

    public void updateAddNewAttribute(ProductCatalogDTO dto) {
        Table table = dynamoDB.getTable(TABLE_NAME);

        try {

            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 121)
                .withUpdateExpression("set #na = :val1").withNameMap(new NameMap().with("#na", "NewAttribute"))
                .withValueMap(new ValueMap().withString(":val1", "Some value")).withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

            // Check the response.
            System.out.println("Printing item after adding new attribute...");
            System.out.println(outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println("Failed to add new attribute in " + TABLE_NAME);
            System.err.println(e.getMessage());
        }
    }

    public void updateMultipleAttributes(ProductCatalogDTO dto) {

        Table table = dynamoDB.getTable(TABLE_NAME);

        try {

            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 120)
                .withUpdateExpression("add #a :val1 set #na=:val2")
                .withNameMap(new NameMap().with("#a", "Authors").with("#na", "NewAttribute"))
                .withValueMap(
                    new ValueMap().withStringSet(":val1", "Author YY", "Author ZZ").withString(":val2", "someValue"))
                .withReturnValues(ReturnValue.ALL_NEW);

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

            // Check the response.
            System.out.println("Printing item after multiple attribute update...");
            System.out.println(outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println("Failed to update multiple attributes in " + TABLE_NAME);
            System.err.println(e.getMessage());

        }
    }

    public void updateExistingAttributeConditionally(ProductCatalogDTO dto) {

        Table table = dynamoDB.getTable(TABLE_NAME);

        try {

            // Specify the desired price (25.00) and also the condition (price =
            // 20.00)

            UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", 120)
                .withReturnValues(ReturnValue.ALL_NEW).withUpdateExpression("set #p = :val1")
                .withConditionExpression("#p = :val2").withNameMap(new NameMap().with("#p", "Price"))
                .withValueMap(new ValueMap().withNumber(":val1", 25).withNumber(":val2", 20));

            UpdateItemOutcome outcome = table.updateItem(updateItemSpec);

            // Check the response.
            System.out.println("Printing item after conditional update to new attribute...");
            System.out.println(outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println("Error updating item in " + TABLE_NAME);
            System.err.println(e.getMessage());
        }
    }

    public void deleteItem(Long idnumber) {

        Table table = dynamoDB.getTable(TABLE_NAME);

        try {

            DeleteItemSpec deleteItemSpec = new DeleteItemSpec().withPrimaryKey("Id", 120)
                .withConditionExpression("#ip = :val").withNameMap(new NameMap().with("#ip", "InPublication"))
                .withValueMap(new ValueMap().withBoolean(":val", false)).withReturnValues(ReturnValue.ALL_OLD);

            DeleteItemOutcome outcome = table.deleteItem(deleteItemSpec);

            // Check the response.
            System.out.println("Printing item that was deleted...");
            System.out.println(outcome.getItem().toJSONPretty());

        }
        catch (Exception e) {
            System.err.println("Error deleting item in " + TABLE_NAME);
            System.err.println(e.getMessage());
        }
    }

   
}