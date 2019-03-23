package com.bartender.functions;

import com.bartender.dao.ReadCommandBeerRepository;
import com.bartender.service.ReadCommandBeerService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ReadCommandBeerServiceShould implements JsonTools {
    private ReadCommandBeerRepository readCommandBeerRepository = mock(ReadCommandBeerRepository.class);
    private ReadCommandBeerService readCommandFoodService = new ReadCommandBeerService(
            readCommandBeerRepository
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
        verify(readCommandBeerRepository, times(1)).saveCommand(stringCaptor.capture());*/

        // Then
        /*assertThat(stringCaptor.getValue()).isEqualTo(givenId);*/
    }

}
