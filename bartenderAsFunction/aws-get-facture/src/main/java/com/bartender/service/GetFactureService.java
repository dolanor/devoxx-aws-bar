package com.bartender.service;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bartender.dao.GetFactureRepository;
import com.bartender.model.Command;
import com.bartender.model.IotEventRequest;
import com.bartender.model.CommandResponse;

public class GetFactureService {

    private static final Logger LOG = LogManager.getLogger(GetFactureService.class);

    private GetFactureRepository getFactureRepository;

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneId.of("UTC"));

    public GetFactureService(GetFactureRepository getFactureRepository) {
        this.getFactureRepository = getFactureRepository;
    }

    public CommandResponse handleInput(IotEventRequest iotEventRequest) {
        LOG.info("Got event: {}", iotEventRequest.getCurrent());

        // TODO 05. generate command (model.command) with date in utc format
/*        Command command = Command.builder()
                .setIdCommand(id.toString())
                .setDateCommand(formatter.format(ZonedDateTime.now(ZoneOffset.UTC)))
                .build();*/

        // TODO 05. save command in dynamo
        //return getFactureRepository.saveCommand(command);
        return CommandResponse.builder()
                .setIdClient("user3")
                .build();
    }

}
