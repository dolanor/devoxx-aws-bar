package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.model.ApiGatewayResponse;
import com.bartender.model.DrunkClient;
import com.bartender.model.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;

public class RegisterClientHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(RegisterClientHandler.class);

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: {}", input);
        try {
            DrunkClient drunkClient = Json.serializer().mapOrError(input, DrunkClient.class);
            if (StringUtils.isEmpty(drunkClient.getId())) {
                drunkClient.setId(UUID.randomUUID().toString());
            }
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(drunkClient)
                    .build();
        } catch (Exception ex) {
            LOG.error("Error registering client", ex);
            return ApiGatewayResponse.builder()
                    .setStatusCode(400)
                    .setObjectBody(ex)
                    .build();
        }
    }
}
