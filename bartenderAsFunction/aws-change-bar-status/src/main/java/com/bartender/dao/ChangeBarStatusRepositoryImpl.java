package com.bartender.dao;

import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iotdataplane.IotDataPlaneClient;
import software.amazon.awssdk.services.iotdataplane.model.GetThingShadowRequest;
import software.amazon.awssdk.services.iotdataplane.model.UpdateThingShadowRequest;

/**
 * Some examples are specified here
 * https://github.com/aws/aws-iot-device-sdk-java/blob/master/aws-iot-device-sdk-java-samples/src/main/java/com/amazonaws/services/iot/client/sample/shadowEcho/ShadowEchoSample.java
 */
public class ChangeBarStatusRepositoryImpl implements ChangeBarStatusRepository {

    private static final Logger LOG = LogManager.getLogger(ChangeBarStatusRepositoryImpl.class);

    @Override
    public CommandResponse saveCommand(CommandRequest commandRequest) {
        try (IotDataPlaneClient client = newClient()) {
            /*final UpdateThingShadowRequest request = UpdateThingShadowRequest.builder()
                    .thingName(commandRequest.getUserId())
                    .payload(buildPayload())
                    .build();*/
            /*client.updateThingShadow(request);*/

            GetThingShadowRequest request = GetThingShadowRequest.builder()
                    .thingName(commandRequest.getUserId())
                    .build();
            String utfString = client.getThingShadow(request).payload()
                    .asUtf8String();

            return CommandResponse.builder()
                    .setIdClient(commandRequest.getUserId())
                    .setDocument(utfString)
                    .build();
        }
    }

    private SdkBytes buildPayload() {
        return SdkBytes.fromUtf8String("{ \"state\": {\"reported\":{ \"r\": {}}}}");
    }

    // TODO 04. create a new client
    private IotDataPlaneClient newClient() {
        return IotDataPlaneClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }
}
