package com.bartender.service;

import com.bartender.dao.ReadCommandFoodRepository;
import com.bartender.model.Command;
import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;
import com.bartender.model.Json;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

public class ReadCommandFoodService {

    private static final Logger LOG = LogManager.getLogger(ReadCommandFoodService.class);

    private ReadCommandFoodRepository readCommandFoodRepository;

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneId.of("UTC"));

    public ReadCommandFoodService(ReadCommandFoodRepository readCommandFoodRepository) {
        this.readCommandFoodRepository = readCommandFoodRepository;
    }

    public CommandResponse handleInput(Map<String, Object> input) {
        CommandRequest commandRequest = Json.serializer().mapOrError(input, CommandRequest.class);
        LOG.info("Got {} in Read Command Food", commandRequest);

        // TODO 1. generate id to the command (uuid)
        UUID id = UUID.randomUUID();
        LOG.info("Food: {}", commandRequest.getFood());

        // TODO 2. generate command (model.command) with date in utc format
        Command command = Command.builder()
                .setIdCommand(id.toString())
                .setDateCommand(formatter.format(ZonedDateTime.now(ZoneOffset.UTC)))
                .setFood(commandRequest.getFood())
                .build();

        // TODO 3. save command in dynamo
        return readCommandFoodRepository.saveCommand(command);
    }

}
