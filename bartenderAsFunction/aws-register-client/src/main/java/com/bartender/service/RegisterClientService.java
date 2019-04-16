package com.bartender.service;

import com.bartender.dao.RegisterClientRepository;
import com.bartender.model.DrunkClientResponse;
import com.bartender.model.DrunkClientRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.utils.StringUtils;

import java.util.UUID;

public class RegisterClientService {

    private static final Logger LOG = LogManager.getLogger(RegisterClientService.class);

    private RegisterClientRepository registerClientRepository;

    public RegisterClientService(RegisterClientRepository registerClientRepository) {
        this.registerClientRepository = registerClientRepository;
    }

    public DrunkClientResponse handleInput(DrunkClientRequest drunkClient) {
        LOG.info("Got {} in Register Client", drunkClient);

        String id = StringUtils.isEmpty(drunkClient.getId())
                ? UUID.randomUUID().toString()
                : drunkClient.getId();

        // TODO: call the repository using the id
        //return registerClientRepository.registerNewDevice(id);
        return null;
    }

}
