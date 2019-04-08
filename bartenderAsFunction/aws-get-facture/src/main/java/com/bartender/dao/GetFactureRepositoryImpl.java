package com.bartender.dao;

import com.bartender.model.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Some examples are specified here
 * https://github.com/aws/aws-sdk-java-v2/blob/master/services/dynamodb/src/it/java/software/amazon/awssdk/services/dynamodb/BaseResultIntegrationTest.java
 */
public class GetFactureRepositoryImpl implements GetFactureRepository {

    private static final Logger LOG = LogManager.getLogger(GetFactureRepositoryImpl.class);

    private static final String tableName = System.getenv("TABLE_COMMANDS");

    @Override
    public List<Command> getCommands(String deviceId) {
        try (DynamoDbClient dynamoDB = newConnection()) {
            ScanRequest request = ScanRequest.builder()
                    .tableName(tableName) // TODO 05. use the table name got as an ENV variable
                    .build();
            final ScanResponse scan = dynamoDB.scan(request);

            return scan.items().stream()
                    .map(result -> Command.from(deviceId, result)) // TODO 05. call the CommandResponse::from
                    .collect(toList());
        }
    }

    @Override
    public Command saveCommand(Command command) {
        try (DynamoDbClient dynamoDB = newConnection()) {
            LOG.info("About to write command: {} in {}", command.marshal(), tableName);

            PutItemRequest request =  PutItemRequest.builder()
                    .item(command.marshal()) // TODO 05. marshall the command
                    .tableName(tableName) // TODO 05. use the table name got as an ENV variable
                    .build();

            dynamoDB.putItem(request);
            return command;
        }
    }

    private DynamoDbClient newConnection() {
        return DynamoDbClient.builder()
                .region(Region.EU_WEST_1)
                .build();
    }
}
