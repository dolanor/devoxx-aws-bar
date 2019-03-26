package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.dao.ReadCommandBeerRepositoryImpl;
import com.bartender.model.ApiGatewayResponse;
import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;
import com.bartender.service.ReadCommandBeerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadCommandBeerHandler implements RequestHandler<CommandRequest, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(ReadCommandBeerHandler.class);

    private ReadCommandBeerService service = new ReadCommandBeerService(
            new ReadCommandBeerRepositoryImpl()
    );

    @Override
    public ApiGatewayResponse handleRequest(CommandRequest commandRequest, Context context) {
        LOG.info("received beer: {}", commandRequest);
        try {
            CommandResponse commandResponse = service.handleInput(commandRequest);
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(commandResponse)
                    .build();
        } catch (Exception ex) {
            LOG.error("Error reading command beer", ex);
            return ApiGatewayResponse.builder()
                    .setStatusCode(400)
                    .setObjectBody(ex)
                    .build();
        }
    }
}
