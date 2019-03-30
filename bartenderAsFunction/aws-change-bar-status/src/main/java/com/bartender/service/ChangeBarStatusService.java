package com.bartender.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bartender.dao.ChangeBarStatusRepository;
import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;

public class ChangeBarStatusService {

    private static final Logger LOG = LogManager.getLogger(ChangeBarStatusService.class);

    private ChangeBarStatusRepository changeBarStatusRepository;

    public ChangeBarStatusService(ChangeBarStatusRepository changeBarStatusRepository) {
        this.changeBarStatusRepository = changeBarStatusRepository;
    }

    public CommandResponse handleInput(CommandRequest commandRequest) {
        LOG.info("Got: {}", commandRequest);
        // TODO 04. save to the shadow
        //return changeBarStatusRepository.saveCommand(null);
        return CommandResponse.builder()
                .setIdClient(commandRequest.getUserId())
                .build();
    }

}
