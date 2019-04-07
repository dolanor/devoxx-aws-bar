package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.core.SdkField;
import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
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
    public List<CommandResponse> getCommands(String deviceId) {
        try (DynamoDbClient dynamoDB = newConnection()) {
            ScanRequest request = ScanRequest.builder()
                    .tableName(tableName) // TODO 05. use the table name got as an ENV variable
                    .build();
            final ScanResponse scan = dynamoDB.scan(request);

            return scan.items().stream()
                    .map(result -> CommandResponse.from(deviceId, result)) // TODO 05. call the CommandResponse::from
                    .collect(toList());
        }
    }

    @Override
    public CommandResponse saveCommand(Command command) {
        try (DynamoDbClient dynamoDB = newConnection()) {
            //LOG.info("About to write command: {} in {}", command.marshal(), tableName);


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
