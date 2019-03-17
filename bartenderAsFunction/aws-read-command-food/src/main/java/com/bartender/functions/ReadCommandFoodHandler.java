package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.dao.ReadCommandFoodRepositoryImpl;
import com.bartender.model.ApiGatewayResponse;
import com.bartender.model.CommandResponse;
import com.bartender.service.ReadCommandFoodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ReadCommandFoodHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(ReadCommandFoodHandler.class);

    private ReadCommandFoodService service = new ReadCommandFoodService(
            new ReadCommandFoodRepositoryImpl()
    );

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        LOG.info("received: {}", input);
        try {
            CommandResponse commandResponse = service.handleInput(input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(commandResponse)
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
