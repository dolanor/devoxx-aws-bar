package com.bartender.service;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bartender.dao.ChangeBarStatusRepository;
import com.bartender.model.Command;
import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;

public class ChangeBarStatusService {

    private static final Logger LOG = LogManager.getLogger(ChangeBarStatusService.class);

    private ChangeBarStatusRepository changeBarStatusRepository;

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneId.of("UTC"));

    public ChangeBarStatusService(ChangeBarStatusRepository changeBarStatusRepository) {
        this.changeBarStatusRepository = changeBarStatusRepository;
    }

    public CommandResponse handleInput(CommandRequest commandRequest) {
        // TODO 04. generate id to the command (uuid)
        UUID id = UUID.randomUUID();
        LOG.info("Got: {}", commandRequest);

        // TODO 04. generate command (model.command) with date in utc format
        Command command = Command.builder()
                .setIdCommand(id.toString())
                .setDateCommand(formatter.format(ZonedDateTime.now(ZoneOffset.UTC)))
                .setFood(commandRequest.getFood())
                .build();

        // TODO 04. save command in dynamo
        return changeBarStatusRepository.saveCommand(command);
    }

}
