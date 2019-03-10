package com.bartender.functions;

import com.bartender.model.ApiGatewayResponse;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;

class RegisterClientHandlerShould implements JsonTools {
    private RegisterClientHandler subject = new RegisterClientHandler();

    @Test
    void return_same_id_if_specified() {
        // Given
        Map<String, Object> input = new HashMap<>();
        input.put("id", UUID.randomUUID().toString());

        // When
        ApiGatewayResponse output = subject.handleRequest(input, new TestContext());

        // Then
        assertThat(output.getStatusCode()).isEqualTo(200);
        final Map<String, Object> response = jsonToMap(output.getBody());
        assertThat(response.get("id")).isNotNull();
    }

}
