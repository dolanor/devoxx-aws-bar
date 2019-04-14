package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

/**
 * Some examples are specified here
 * https://github.com/aws/aws-sdk-java-v2/blob/master/services/dynamodb/src/it/java/software/amazon/awssdk/services/dynamodb/BaseResultIntegrationTest.java
 */
public class ReadCommandFoodRepositoryImpl implements ReadCommandFoodRepository {

    private static final Logger LOG = LogManager.getLogger(ReadCommandFoodRepositoryImpl.class);

    private static final String tableName = System.getenv("TABLE_COMMANDS");

    @Override
    public CommandResponse saveCommand(Command command) {
        try (DynamoDbClient dynamoDB = newConnection()) {
            LOG.info("About to write command: {} in {}", command.marshal(), tableName);
            PutItemRequest request =  PutItemRequest.builder()
                    .item(command.marshal()) // TODO marshall the command
                    .tableName(tableName) // TODO use the table name got as an ENV variable
                    .build();
            dynamoDB.putItem(request);
            return CommandResponse.builder()
                    .setIdClient(command.getClient())
                    .build();
        }
    }

    private DynamoDbClient newConnection() {
        return DynamoDbClient.builder()
                .region(Region.EU_WEST_1)
                .build();
    }
}
