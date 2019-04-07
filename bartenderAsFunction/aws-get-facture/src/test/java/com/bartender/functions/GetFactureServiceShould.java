package com.bartender.functions;

import com.bartender.dao.GetFactureRepository;
import com.bartender.model.*;
import com.bartender.service.GetFactureService;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetFactureServiceShould implements JsonTools {

    private final GetFactureRepository factureRepository = mock(GetFactureRepository.class);
    private GetFactureService factureService = new GetFactureService(factureRepository);

    @Test
    void invoke_repository_when_states_are_equal() {
        // Given
        final IotShadowState shadowState = new IotShadowState()
                .setDesired(new ClientObjectState().setBarStatus(IotShadowState.CLOSED))
                .setReported(new ClientObjectState().setBarStatus(IotShadowState.CLOSED));
        IotEventRequest iotEventRequest = new IotEventRequest()
                .setCurrent(new IotShadowDoc().setState(shadowState));

        final Command expectedResponse = Command.builder()
                .setClient("123456")
                .setFood(new Item()
                        .setServed(false))
                .setBeer(new Item()
                        .setServed(false))
                .build();
        when(factureRepository.getCommands(any())).thenReturn(Collections.singletonList(expectedResponse));
        when(factureRepository.saveCommand(any())).thenReturn(expectedResponse);

        // When
        final List<Command> response = factureService.handleInput(iotEventRequest);

        // Then
        assertThat(response).hasSize(1);
        assertThat(response.get(0).getClient()).isEqualTo(expectedResponse.getClient());
        assertThat(response.get(0).getFood().isServed()).isTrue();
        assertThat(response.get(0).getBeer().isServed()).isTrue();
    }

    @Test
    void invoke_repository_when_states_are_equal_when_no_items() {
        // Given
        final IotShadowState shadowState = new IotShadowState()
                .setDesired(new ClientObjectState().setBarStatus(IotShadowState.CLOSED))
                .setReported(new ClientObjectState().setBarStatus(IotShadowState.CLOSED));
        IotEventRequest iotEventRequest = new IotEventRequest()
                .setCurrent(new IotShadowDoc().setState(shadowState));

        final Command expectedResponse = Command.builder()
                .setClient("123456")
                .build();
        when(factureRepository.getCommands(any())).thenReturn(Collections.singletonList(expectedResponse));
        when(factureRepository.saveCommand(any())).thenReturn(expectedResponse);

        // When
        final List<Command> response = factureService.handleInput(iotEventRequest);

        // Then
        assertThat(response).hasSize(1);
        assertThat(response.get(0).getClient()).isEqualTo(expectedResponse.getClient());
    }

    @Test
    void reject_because_no_desired_state() {
        // Given
        final IotShadowState shadowState = new IotShadowState()
                .setDesired(null)
                .setReported(new ClientObjectState().setBarStatus(IotShadowState.CLOSED));
        IotEventRequest iotEventRequest = new IotEventRequest()
                .setCurrent(new IotShadowDoc().setState(shadowState));

        final Command expectedResponse = Command.builder()
                .setClient("123456").build();
        when(factureRepository.getCommands(any())).thenReturn(Collections.singletonList(expectedResponse));

        // When
        final List<Command> response = factureService.handleInput(iotEventRequest);
        assertThat(response).isEmpty();
    }

    @Test
    void reject_because_no_reported_state() {
        // Given
        final IotShadowState shadowState = new IotShadowState()
                .setDesired(new ClientObjectState().setBarStatus(IotShadowState.CLOSED))
                .setReported(null);
        IotEventRequest iotEventRequest = new IotEventRequest()
                .setCurrent(new IotShadowDoc().setState(shadowState));

        final Command expectedResponse = Command.builder()
                .setClient("123456").build();
        when(factureRepository.getCommands(any())).thenReturn(Collections.singletonList(expectedResponse));

        // When
        final List<Command> response = factureService.handleInput(iotEventRequest);
        assertThat(response).isEmpty();
    }

    @Test
    void invoke_repository_when_states_are_NOT_equal() {
        // Given
        final IotShadowState shadowState = new IotShadowState()
                .setDesired(new ClientObjectState().setBarStatus(IotShadowState.CLOSED))
                .setReported(new ClientObjectState().setBarStatus(UUID.randomUUID().toString()));
        IotEventRequest iotEventRequest = new IotEventRequest()
                .setCurrent(new IotShadowDoc().setState(shadowState));

        final Command expectedResponse = Command.builder()
                .setClient("123456").build();
        when(factureRepository.getCommands(any())).thenReturn(Collections.singletonList(expectedResponse));

        // When
        final List<Command> response = factureService.handleInput(iotEventRequest);
        assertThat(response).isEmpty();
    }

}
