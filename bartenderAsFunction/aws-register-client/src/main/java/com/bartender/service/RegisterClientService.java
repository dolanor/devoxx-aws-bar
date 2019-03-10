package com.bartender.service;

import com.bartender.dao.RegisterClientRepository;
import com.bartender.model.DrunkClient;
import com.bartender.model.Json;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.UUID;

public class RegisterClientService {

    private static final Logger LOG = LogManager.getLogger(RegisterClientService.class);

    private RegisterClientRepository registerClientRepository;
    private final String policyDrunkClient;

    public RegisterClientService(RegisterClientRepository registerClientRepository) {
        this.registerClientRepository = registerClientRepository;
        this.policyDrunkClient = System.getenv("POLICY_DRUNK_CLIENT");
    }

    public DrunkClient handleInput(Map<String, Object> input) {
        DrunkClient drunkClient = Json.serializer().mapOrError(input, DrunkClient.class);
        if (StringUtils.isEmpty(drunkClient.getId())) {
            drunkClient.setId(UUID.randomUUID().toString());
        }
        registerClientRepository.registerNewDevice(drunkClient);
        return drunkClient;
    }

}
