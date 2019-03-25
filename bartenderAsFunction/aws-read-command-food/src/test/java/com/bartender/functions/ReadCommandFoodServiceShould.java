package com.bartender.functions;

import com.bartender.model.Command;
import com.bartender.model.Item;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;

class ReadCommandFoodServiceShould implements JsonTools {

    @Test
    void verify_food_is_marshaled() {
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
        Item food = new Item()
                .setItem("burger")
                .setAmount(4)
                .setServed(false);
        Item beer = new Item()
                .setItem("burger")
                .setAmount(4)
                .setServed(false);

        // When
        Command command = Command.builder()
                .setIdCommand(null)
                .setClient(null)
                .setFood(food)
                .setBeer(beer)
                .build();
        final Map<String, AttributeValue> marshaled = command.marshal();

        System.out.println(marshaled);
        assertThat(marshaled).containsKeys("food", "beer");
    }

}
