package com.bartender.functions;

import com.bartender.model.Command;
import com.bartender.model.Item;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;

class ReadCommandBeerServiceShould implements JsonTools {

    @Test
    void verify_beer_is_marshaled() {
        // Given
        Item item = new Item()
                .setItem("burger")
                .setAmount(4)
                .setServed(false);

        assertThat(item.marshal()).containsKeys("item", "amount", "served");
    }

    @Test
    void verify_command_is_marshaled() {
        // Given
        Item beer = new Item()
                .setItem("burger")
                .setAmount(4)
                .setServed(false);

        // When
        Command command = Command.builder()
                .setIdCommand(null)
                .setClient(null)
                .setBeer(beer)
                .build();
        final Map<String, AttributeValue> marshaled = command.marshal();

        assertThat(marshaled).containsKeys("beer");
    }

}
