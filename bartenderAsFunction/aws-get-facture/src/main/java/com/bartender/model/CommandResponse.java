package com.bartender.model;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.Objects;

public class CommandResponse {
    private String idClient;

    public static Builder builder() {
        return new Builder();
    }

    // TODO 05. Transform the DynamoDB response into a CommandResponse
    public static CommandResponse from(String deviceId, Map<String, AttributeValue> result) {
        return builder()
                .setIdClient(deviceId)
                .build();
    }

    public static class Builder {
        private String idClient;

        public Builder setIdClient(String idClient) {
            this.idClient = idClient;
            return this;
        }

        public CommandResponse build() {
            final CommandResponse response = new CommandResponse();
            response.idClient = idClient;
            return response;
        }
    }

    public String getIdClient() {
        return idClient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandResponse)) return false;
        CommandResponse that = (CommandResponse) o;
        return Objects.equals(idClient, that.idClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient);
    }

    @Override
    public String toString() {
        return "CommandResponse{" +
                "id='" + idClient + '\'' +
                '}';
    }
}
