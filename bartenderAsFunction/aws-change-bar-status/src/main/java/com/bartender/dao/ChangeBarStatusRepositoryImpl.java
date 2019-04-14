package com.bartender.dao;

import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;
import com.bartender.model.Json;
import com.bartender.model.ShadowState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iotdataplane.IotDataPlaneClient;
import software.amazon.awssdk.services.iotdataplane.model.UpdateThingShadowRequest;
import software.amazon.awssdk.services.iotdataplane.model.UpdateThingShadowResponse;

/**
 * Some examples are specified here
 * https://github.com/aws/aws-iot-device-sdk-java/blob/master/aws-iot-device-sdk-java-samples/src/main/java/com/amazonaws/services/iot/client/sample/shadowEcho/ShadowEchoSample.java
 */
public class ChangeBarStatusRepositoryImpl implements ChangeBarStatusRepository {

    private static final Logger LOG = LogManager.getLogger(ChangeBarStatusRepositoryImpl.class);

    @Override
    public CommandResponse saveCommand(CommandRequest commandRequest) {
        try (IotDataPlaneClient client = newClient()) {
            LOG.info("Building the shadow request for thing: {}", commandRequest.getUserId());

            // TODO create a new 'Closed ShadowState' and set the 'payload' to the builder
            final SdkBytes sdkBytes = ShadowState.Closed().buildPayload()
                    .orElseThrow(() -> new IllegalStateException("Impossible to generate ShadowRequest"));

            // TODO create a new UpdateThingShadowRequest with a 'thingName'
            final UpdateThingShadowRequest request = UpdateThingShadowRequest.builder()
                    .thingName(commandRequest.getUserId())
                    .payload(sdkBytes)
                    .build();

            LOG.info("Executing the shadow request: {}", Json.serializer().toJson(ShadowState.Closed()).orElse("?"));
            // TODO 'updateThingShadow'
            final UpdateThingShadowResponse response = client.updateThingShadow(request);

            return CommandResponse.builder()
                    .setIdClient(commandRequest.getUserId())
                    .setDocument(response.payload().asUtf8String())
                    .build();
        }
    }

    // TODO create a new client
    private IotDataPlaneClient newClient() {
        return IotDataPlaneClient.builder()
                .region(Region.EU_WEST_1)
                .build();
    }
}
