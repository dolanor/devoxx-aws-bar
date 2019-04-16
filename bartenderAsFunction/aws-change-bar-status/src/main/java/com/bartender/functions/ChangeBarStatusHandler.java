package com.bartender.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.bartender.dao.ChangeBarStatusRepositoryImpl;
import com.bartender.model.ApiGatewayResponse;
import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;
import com.bartender.service.ChangeBarStatusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

public class ChangeBarStatusHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private static final Logger LOG = LogManager.getLogger(ChangeBarStatusHandler.class);

    private ChangeBarStatusService service = new ChangeBarStatusService(
            new ChangeBarStatusRepositoryImpl()
    );

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> commandRequest, Context context) {
        LOG.info("received change bar status: {}", commandRequest);

        try {
            // TODO get the attribute 'path' and build a CommandRequest 'from' the value
            final CommandRequest request = null; /*Optional.ofNullable(commandRequest.get("path"))
                    .map(Object::toString)
                    .flatMap(CommandRequest::from)
                    .orElseThrow(IllegalArgumentException::new);*/

            CommandResponse commandResponse = service.handleInput(request);
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(commandResponse)
                    .build();
        } catch(IllegalArgumentException ex) {
            LOG.error("Error parsing data for changing bar status", ex);
            return ApiGatewayResponse.builder()
                    .setStatusCode(422)
                    .setObjectBody(ex)
                    .build();
        } catch (Exception ex) {
            LOG.error("Unknown error while changing bar status", ex);
            return ApiGatewayResponse.builder()
                    .setStatusCode(400)
                    .setObjectBody(ex)
                    .build();
        }
    }
}
