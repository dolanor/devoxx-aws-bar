package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.dao.GetFactureRepositoryImpl;
import com.bartender.model.ApiGatewayResponse;
import com.bartender.model.IotEventRequest;
import com.bartender.service.GetFactureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetFactureHandler implements RequestHandler<IotEventRequest, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(GetFactureHandler.class);

    private GetFactureService service = new GetFactureService(
            new GetFactureRepositoryImpl()
    );

    @Override
    public ApiGatewayResponse handleRequest(IotEventRequest iotEventRequest, Context context) {
        LOG.info("received iot event: {}", iotEventRequest);
        try {
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(service.handleInput(iotEventRequest))
                    .build();
        } catch (Exception ex) {
            LOG.error("Error reading iot event", ex);
            return ApiGatewayResponse.builder()
                    .setStatusCode(400)
                    .setObjectBody(ex)
                    .build();
        }
    }
}
