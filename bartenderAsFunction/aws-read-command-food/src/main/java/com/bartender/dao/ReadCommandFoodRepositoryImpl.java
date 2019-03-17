package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;
import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;

/**
 * Some examples are specified here
 * https://github.com/aws/aws-sdk-java-v2/blob/master/services/dynamodb/src/it/java/software/amazon/awssdk/services/dynamodb/BaseResultIntegrationTest.java
 */
public class ReadCommandFoodRepositoryImpl implements ReadCommandFoodRepository {

    private static final String tableName = System.getenv("TABLE_COMMANDS");

    @Override
    public CommandResponse saveCommand(Command command) {
        try (DynamoDbClient dynamoDB = newConnection()) {
            PutItemRequest request =  PutItemRequest.builder()
                    .item(marshalCommand(command))
                    .tableName(tableName)
                    .build();
            dynamoDB.putItem(request);
            return CommandResponse.builder()
                    .setIdClient(command.getClient())
                    .build();
        }
    }

    private HashMap<String, AttributeValue> marshalCommand(Command command) {
        final HashMap<String, AttributeValue> map = new HashMap<>();
        map.put("IdCommand", AttributeValue.builder().s(command.getIdCommand()).build());
        map.put("DateCommand", AttributeValue.builder().s(command.getDateCommand()).build());
        map.put("Client", AttributeValue.builder().s(command.getClient()).build());
        // TODO: add missing fields
        return map;
    }

    private DynamoDbClient newConnection() {
        return DynamoDbClient.builder()
                .region(Region.US_WEST_1)
                .build();
    }
}
