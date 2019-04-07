package com.bartender.model;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;

public class Command {
    private String id;
    private String dateCommand;
    private Item beer;
    private Item food;
    private String client;

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, AttributeValue> marshal() {
        final HashMap<String, AttributeValue> map = new HashMap<>();
        Optional.ofNullable(this.getId()).ifPresent(value ->
                map.put("id", AttributeValue.builder().s(value).build())
         );
        Optional.ofNullable(this.getDateCommand()).ifPresent(value ->
                map.put("date", AttributeValue.builder().s(value).build())
        );
        Optional.ofNullable(this.getClient()).ifPresent(value ->
                map.put("client", AttributeValue.builder().s(value).build())
        );
        Optional.ofNullable(beer).ifPresent(actualBeer ->
                map.put("beer", AttributeValue.builder().m(actualBeer.marshal()).build())
        );
        return map;
    }

    public static Command from(String deviceId, Map<String, AttributeValue> result) {
        return builder()
                .setClient(deviceId)
                .build();
    }

    public static class Builder {
        private String id;
        private String dateCommand;
        private Item beer;
        private Item food;
        private String client;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setDateCommand(String dateCommand) {
            this.dateCommand = dateCommand;
            return this;
        }

        public Builder setClient(String client) {
            this.client = client;
            return this;
        }

        public Builder setBeer(Item beer) {
            this.beer = beer;
            return this;
        }

        public Builder setFood(Item food) {
            this.food = food;
            return this;
        }

        public Command build() {
            final Command command = new Command();
            command.id = id;
            command.dateCommand = dateCommand;
            command.beer = beer;
            command.food = food;
            command.client = client;
            return command;
        }
    }

    public String getId() {
        return id;
    }

    public String getDateCommand() {
        return dateCommand;
    }

    public Item getBeer() {
        return beer;
    }

    public Item getFood() {
        return food;
    }

    public String getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;
        Command command = (Command) o;
        return Objects.equals(id, command.id) &&
                Objects.equals(dateCommand, command.dateCommand) &&
                Objects.equals(beer, command.beer) &&
                Objects.equals(food, command.food) &&
                Objects.equals(client, command.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCommand, beer, food, client);
    }

    @Override
    public String toString() {
        return "Command{" +
                "id='" + id + '\'' +
                ", dateCommand='" + dateCommand + '\'' +
                ", beer=" + beer +
                ", food=" + food +
                ", client='" + client + '\'' +
                '}';
    }
}
