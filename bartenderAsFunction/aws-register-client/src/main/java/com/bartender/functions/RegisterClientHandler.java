package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.dao.RegisterClientRepositoryImpl;
import com.bartender.model.*;
import com.bartender.service.RegisterClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterClientHandler implements RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(RegisterClientHandler.class);

    private RegisterClientService service = new RegisterClientService(
            new RegisterClientRepositoryImpl()
    );

    @Override
    public ApiGatewayResponse handleRequest(ApiGatewayRequest input, Context context) {
        LOG.info("received: {}", input);
        try {
            // TODO: use the json deserializer to parse the body from the input
            DrunkClientRequest request = null; //Json.instance().parse(/* get the body from input */, DrunkClientRequest.class);
            DrunkClientResponse drunkClientResponse = service.handleInput(request);
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
