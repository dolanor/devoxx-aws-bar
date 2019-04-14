package com.bartender.service;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bartender.dao.ReadCommandBeerRepository;
import com.bartender.model.Command;
import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;

public class ReadCommandBeerService {

    private static final Logger LOG = LogManager.getLogger(ReadCommandBeerService.class);

    private ReadCommandBeerRepository readCommandBeerRepository;

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneId.of("UTC"));

    public ReadCommandBeerService(ReadCommandBeerRepository readCommandBeerRepository) {
        this.readCommandBeerRepository = readCommandBeerRepository;
    }

    public CommandResponse handleInput(CommandRequest commandRequest) {
        // TODO generate id to the command (uuid)
        UUID id = UUID.randomUUID();
        LOG.info("Got: {}", commandRequest);

        // TODO generate command (model.command) with date in utc format
        Command command = Command.builder()
                .setIdCommand(id.toString())
                .setDateCommand(formatter.format(ZonedDateTime.now(ZoneOffset.UTC)))
                .setBeer(commandRequest.getBeer())
                .build();

        // TODO save command in dynamo
        return readCommandBeerRepository.saveCommand(command);
    }

}
