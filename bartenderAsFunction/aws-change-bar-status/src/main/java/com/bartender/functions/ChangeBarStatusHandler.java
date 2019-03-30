package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.dao.ChangeBarStatusRepositoryImpl;
import com.bartender.model.ApiGatewayResponse;
import com.bartender.service.ChangeBarStatusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ChangeBarStatusHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(ChangeBarStatusHandler.class);

    private ChangeBarStatusService service = new ChangeBarStatusService(
            new ChangeBarStatusRepositoryImpl()
    );

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> commandRequest, Context context) {
        LOG.info("received change bar status: {}", commandRequest);
        try {
            // CommandResponse commandResponse = service.handleInput(commandRequest);
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody("Everything went ok!")
                    .build();
        } catch (Exception ex) {
            LOG.error("Error changing status bar", ex);
            return ApiGatewayResponse.builder()
                    .setStatusCode(400)
                    .setObjectBody(ex)
                    .build();
        }
    }
}
