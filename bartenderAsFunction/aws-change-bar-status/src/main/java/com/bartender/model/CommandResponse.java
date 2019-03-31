package com.bartender.model;

import java.util.Objects;

public class CommandResponse {
    private String idClient;
    private String document;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String idClient;
        private String document;

        public Builder setIdClient(String idClient) {
            this.idClient = idClient;
            return this;
        }

        public Builder setDocument(String document) {
            this.document = document;
            return this;
        }

        public CommandResponse build() {
            final CommandResponse response = new CommandResponse();
            response.idClient = idClient;
            response.document = document;
            return response;
        }
    }

    public String getIdClient() {
        return idClient;
    }

    public String getDocument() {
        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandResponse)) return false;
        CommandResponse that = (CommandResponse) o;
        return Objects.equals(idClient, that.idClient) &&
                Objects.equals(document, that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, document);
    }

    @Override
    public String toString() {
        return "CommandResponse{" +
                "idClient='" + idClient + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
