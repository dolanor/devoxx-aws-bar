package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.dao.RegisterClientRepositoryImpl;
import com.bartender.model.ApiGatewayResponse;
import com.bartender.model.DrunkClientRequest;
import com.bartender.model.DrunkClientResponse;
import com.bartender.service.RegisterClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterClientHandler implements RequestHandler<DrunkClientRequest, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(RegisterClientHandler.class);

    private RegisterClientService service = new RegisterClientService(
            new RegisterClientRepositoryImpl()
    );

    @Override
    public ApiGatewayResponse handleRequest(DrunkClientRequest input, Context context) {
        LOG.info("received: {}", input);
        try {
            DrunkClientResponse drunkClientResponse = service.handleInput(input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(drunkClientResponse)
                    .build();
        } catch (Throwable ex) {
            LOG.error("Error registering client", ex);
            return ApiGatewayResponse.builder()
                    .setStatusCode(400)
                    .setObjectBody(ex)
                    .build();
        }
    }
}
