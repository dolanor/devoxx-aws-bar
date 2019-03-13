package com.bartender.functions;

import com.bartender.dao.RegisterClientRepository;
import com.bartender.model.DrunkClientResponse;
import com.bartender.service.RegisterClientService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class RegisterClientServiceShould implements JsonTools {
    private RegisterClientRepository registerClientRepository = mock(RegisterClientRepository.class);
    private RegisterClientService registerClientService = new RegisterClientService(
            registerClientRepository
    );

    @Test
    void return_same_id_if_specified() {
        // Given
        Map<String, Object> input = new HashMap<>();
        final String id = UUID.randomUUID().toString();
        input.put("id", id);

        // When
        final DrunkClientResponse response = mock(DrunkClientResponse.class);
        when(response.getIdClient()).thenReturn(id);
        when(registerClientRepository.registerNewDevice(any())).thenReturn(response);
        DrunkClientResponse drunkClientResponse = registerClientService.handleInput(input);

        // Then
        assertThat(drunkClientResponse.getIdClient()).isEqualTo(id);
    }

}
