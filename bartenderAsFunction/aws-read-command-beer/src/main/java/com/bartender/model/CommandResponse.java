package com.bartender.model;

import java.util.Objects;

public class CommandResponse {
    private String idClient;

    public static Builder builder() {
        return new Builder();
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
