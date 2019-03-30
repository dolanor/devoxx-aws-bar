package com.bartender.functions;

import com.bartender.model.CommandRequest;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

class ChangeBarStatusServiceShould implements JsonTools {

    @Test
    void extract_client_id_when_exist() {
        // Given / When
        final String path = "/client/user2/close";
        final Optional<CommandRequest> result = CommandRequest.from(path);
        // Then
        assertThat(result.isPresent()).isTrue();
        result.ifPresent(actualRequest -> assertThat(actualRequest.getUserId()).isEqualTo("user2"));
    }

    @Test
    void not_extract_client_id_when_not_exist() {
        assertThat(CommandRequest.from("/client/").isPresent()).isFalse();
        assertThat(CommandRequest.from("/client/close").isPresent()).isFalse();
        assertThat(CommandRequest.from("/").isPresent()).isFalse();
        assertThat(CommandRequest.from("").isPresent()).isFalse();
    }
}
