package com.bartender.service;

import com.bartender.dao.RegisterClientRepository;
import com.bartender.model.DrunkClient;
import com.bartender.model.Json;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.UUID;

public class RegisterClientService {

    private RegisterClientRepository registerClientRepository;

    public RegisterClientService(RegisterClientRepository registerClientRepository) {
        this.registerClientRepository = registerClientRepository;
    }

    public DrunkClient handleInput(Map<String, Object> input) {
        final String policyDrunkClient = System.getenv("POLICY_DRUNK_CLIENT");

        DrunkClient drunkClient = Json.serializer().mapOrError(input, DrunkClient.class);
        if (StringUtils.isEmpty(drunkClient.getId())) {
            drunkClient.setId(UUID.randomUUID().toString());
        }
        registerClientRepository.registerNewDevice(drunkClient);
        return drunkClient;
    }

}
