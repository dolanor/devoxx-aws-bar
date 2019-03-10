package com.bartender.functions;

import com.bartender.dao.RegisterClientRepository;
import com.bartender.model.DrunkClient;
import com.bartender.service.RegisterClientService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

class RegisterClientServiceShould implements JsonTools {
    private RegisterClientRepository registerClientRepository = spy(RegisterClientRepository.class);
    private RegisterClientService registerClientService = new RegisterClientService(
            registerClientRepository
    );

    @Test
    void return_same_id_if_specified() {
        // Given
        Map<String, Object> input = new HashMap<>();
        input.put("id", UUID.randomUUID().toString());

        // When
        doNothing().when(registerClientRepository).registerNewDevice(any());
        DrunkClient drunkClient = registerClientService.handleInput(input);

        // Then
        assertThat(drunkClient.getId()).isNotNull();
    }
}
