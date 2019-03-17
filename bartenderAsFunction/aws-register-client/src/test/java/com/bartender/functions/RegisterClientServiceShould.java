package com.bartender.functions;

import com.bartender.dao.RegisterClientRepository;
import com.bartender.model.DrunkClientResponse;
import com.bartender.service.RegisterClientService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
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
        final String givenId = UUID.randomUUID().toString();
        input.put("id", givenId);

        // When
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        DrunkClientResponse ignoredResponse = registerClientService.handleInput(input);
        verify(registerClientRepository, times(1)).registerNewDevice(stringCaptor.capture());

        // Then
        assertThat(stringCaptor.getValue()).isEqualTo(givenId);
    }
    
    @Test
    void return_new_id_when_none_specified() {
        // Given
        Map<String, Object> input = new HashMap<>();

        // When
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        DrunkClientResponse ignoredResponse = registerClientService.handleInput(input);
        verify(registerClientRepository, times(1)).registerNewDevice(stringCaptor.capture());

        // Then
        assertThat(stringCaptor.getValue()).isNotBlank();
    }

}
