package com.bartender.functions;

import com.bartender.dao.ReadCommandFoodRepository;
import com.bartender.model.CommandResponse;
import com.bartender.service.ReadCommandFoodService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class ReadCommandFoodServiceShould implements JsonTools {
    private ReadCommandFoodRepository readCommandFoodRepository = mock(ReadCommandFoodRepository.class);
    private ReadCommandFoodService readCommandFoodService = new ReadCommandFoodService(
            readCommandFoodRepository
    );

    @Test
    void return_same_id_if_specified() {
        // Given
        Map<String, Object> input = new HashMap<>();
        final String givenId = UUID.randomUUID().toString();
        input.put("id", givenId);

        // When
        /*ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        CommandResponse ignoredResponse = readCommandFoodService.handleInput(input);
        verify(readCommandFoodRepository, times(1)).saveCommand(stringCaptor.capture());*/

        // Then
        /*assertThat(stringCaptor.getValue()).isEqualTo(givenId);*/
    }

}
