package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Some examples are specified here
 * https://github.com/aws/aws-sdk-java-v2/blob/master/services/dynamodb/src/it/java/software/amazon/awssdk/services/dynamodb/BaseResultIntegrationTest.java
 */
public class ChangeBarStatusRepositoryImpl implements ChangeBarStatusRepository {

    private static final Logger LOG = LogManager.getLogger(ChangeBarStatusRepositoryImpl.class);

    @Override
    public CommandResponse saveCommand(Command command) {
        /*try (DynamoDbClient dynamoDB = newConnection()) {
            LOG.info("About to write command: {} in {}", command.marshal(), tableName);
            PutItemRequest request =  PutItemRequest.builder()
                    .item(command.marshal()) // TODO 04. marshall the command
                    .tableName(tableName) // TODO 04. use the table name got as an ENV variable
                    .build();
            dynamoDB.putItem(request);
            return CommandResponse.builder()
                    .setIdClient(command.getClient())
                    .build();
        }*/
        return null;
    }

    /*private DynamoDbClient newConnection() {
        return DynamoDbClient.builder()
                .region(Region.EU_WEST_1)
                .build();
    }*/
}
