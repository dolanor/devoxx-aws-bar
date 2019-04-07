package com.bartender.service;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bartender.dao.GetFactureRepository;
import com.bartender.model.IotEventRequest;
import com.bartender.model.CommandResponse;

public class GetFactureService {

    private static final Logger LOG = LogManager.getLogger(GetFactureService.class);

    private static final String CLOSED = "CLOSED";

    private GetFactureRepository getFactureRepository;

    public GetFactureService(GetFactureRepository getFactureRepository) {
        this.getFactureRepository = getFactureRepository;
    }

    public List<CommandResponse> handleInput(IotEventRequest iotEventRequest) {
        LOG.info("Got IOT event: {}", iotEventRequest.getCurrent());

        // TODO 05. get the reported and desired 'BarStatus'
        final String reportedBarStatus = getOrNull(() -> iotEventRequest.getCurrent().getState().getReported().getBarStatus());
        final String desiredBarStatus = getOrNull(() -> iotEventRequest.getCurrent().getState().getDesired().getBarStatus());

        if (reportedBarStatus != null
                && reportedBarStatus.equals(desiredBarStatus)
                && desiredBarStatus.equals(CLOSED)) {
            // TODO 05. call the repo
            return getFactureRepository.getCommands(iotEventRequest.getDeviceId());
        } else {
            return Collections.emptyList();
        }
    }

    private String getOrNull(Supplier<String> f) {
        try {
            return f.get();
        } catch (Exception ex) {
            return null;
        }
    }

}
