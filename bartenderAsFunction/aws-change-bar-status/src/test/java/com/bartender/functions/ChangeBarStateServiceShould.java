package com.bartender.functions;

import com.bartender.model.CommandRequest;
import com.bartender.model.Json;
import com.bartender.model.ShadowState;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.core.SdkBytes;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

class ChangeBarStateServiceShould implements JsonTools {

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

    @Test
    void transform_to_json() {
        ShadowState state = new ShadowState()
                .setState(new ShadowState.BarState().setDesired(ShadowState.Desired.CLOSED));
        final Optional<String> json = Json.serializer().toJson(state);
        assertThat(json).isEqualTo(Optional.of("{\"state\":{\"desired\":{\"barStatus\":\"CLOSED\"}}}"));
    }

    @Test
    void serialize_to_and_from_sdkbytes() {
        final Optional<SdkBytes> maybeSdkBytes = ShadowState.Closed().buildPayload();
        final SdkBytes sdkBytes = maybeSdkBytes.orElseThrow(() -> new IllegalArgumentException(""));
        assertThat(sdkBytes.asUtf8String()).isEqualTo("{\"state\":{\"desired\":{\"barStatus\":\"CLOSED\"}}}");
    }
}
