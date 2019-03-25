package com.bartender.functions;

import com.bartender.dao.ReadCommandFoodRepository;
import com.bartender.model.CommandResponse;
import com.bartender.model.Item;
import com.bartender.service.ReadCommandFoodService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Hashtable;
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
    void verify_something() {
        // Given
        Item item = new Item()
                .setItem("burger")
                .setAmount(4)
                .setServed(false);

        // When - CommandRequest
        final Map<String, AttributeValue> marshalled = item.marshal();

        System.out.println(marshalled);
        assertThat(marshalled).containsKeys("item", "amount", "served");
    }

}
