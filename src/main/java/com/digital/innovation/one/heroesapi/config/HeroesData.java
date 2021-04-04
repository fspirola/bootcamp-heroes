package com.digital.innovation.one.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import static com.digital.innovation.one.heroesapi.constants.HeroesConstant.REGION_DYNAMO;
import static com.digital.innovation.one.heroesapi.constants.HeroesConstant.ENDPOINT_DYNAMO;

public class HeroesData {
    public static void main(String[] args) throws Exception {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Heroes_Table_Demo2");
        Item hero = new Item()
                .withPrimaryKey("id", "1")
                .withString("name", "Mulher Maravilha")
                .withString("universe", "DC Comics")
                .withString("films", "2");

        PutItemOutcome outcome = table.putItem(hero);
    }
}
