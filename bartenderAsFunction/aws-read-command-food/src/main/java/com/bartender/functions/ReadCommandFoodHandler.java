package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.dao.ReadCommandFoodRepositoryImpl;
import com.bartender.model.ApiGatewayResponse;
import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;
import com.bartender.service.ReadCommandFoodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadCommandFoodHandler implements RequestHandler<CommandRequest, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(ReadCommandFoodHandler.class);

    private ReadCommandFoodService service = new ReadCommandFoodService(
            new ReadCommandFoodRepositoryImpl()
    );

    @Override
    public ApiGatewayResponse handleRequest(CommandRequest commandRequest, Context context) {
        LOG.info("received food: {}", commandRequest);
        try {
            CommandResponse commandResponse = service.handleInput(commandRequest);
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(commandResponse)
                    .build();
        } catch (Exception ex) {
            LOG.error("Error reading command food", ex);
            return ApiGatewayResponse.builder()
                    .setStatusCode(400)
                    .setObjectBody(ex)
                    .build();
        }
    }
}
