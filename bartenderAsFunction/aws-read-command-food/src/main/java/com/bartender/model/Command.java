package com.bartender.model;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.*;

public class Command {
    private String idCommand;
    private String dateCommand;
    private Item food;
    private Item beer;
    private String client;

    public static Builder builder() {
        return new Builder();
    }

    public Map<String, AttributeValue> marshal() {
        final HashMap<String, AttributeValue> map = new HashMap<>();
        // TODO: uncomment this marshalling
        /*Optional.ofNullable(this.getIdCommand()).ifPresent(value ->
                map.put("id", AttributeValue.builder().s(value).build())
         );
        Optional.ofNullable(this.getDateCommand()).ifPresent(value ->
                map.put("date", AttributeValue.builder().s(value).build())
        );
        Optional.ofNullable(this.getClient()).ifPresent(value ->
                map.put("client", AttributeValue.builder().s(value).build())
        );
        Optional.ofNullable(food).ifPresent(actualFood ->
                map.put("food", AttributeValue.builder().m(actualFood.marshal()).build())
        );
        Optional.ofNullable(beer).ifPresent(actualBeer ->
                map.put("beer", AttributeValue.builder().m(actualBeer.marshal()).build())
        );*/
        return map;
    }

    public static class Builder {
        private String idCommand;
        private String dateCommand;
        private Item food;
        private Item beer;
        private String client;

        public Builder setIdCommand(String idCommand) {
            this.idCommand = idCommand;
            return this;
        }

        public Builder setDateCommand(String dateCommand) {
            this.dateCommand = dateCommand;
            return this;
        }

        public Builder setFood(Item food) {
            this.food = food;
            return this;
        }

        public Builder setBeer(Item beer) {
            this.beer = beer;
            return this;
        }

        public Builder setClient(String client) {
            this.client = client;
            return this;
        }

        public Command build() {
            final Command command = new Command();
            command.idCommand = idCommand;
            command.dateCommand = dateCommand;
            command.food = food;
            command.beer = beer;
            command.client =client;
            return command;
        }
    }

    public String getIdCommand() {
        return idCommand;
    }

    public String getDateCommand() {
        return dateCommand;
    }

    public Item getFood() {
        return food;
    }

    public Item getBeer() {
        return beer;
    }

    public String getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;
        Command command = (Command) o;
        return Objects.equals(idCommand, command.idCommand) &&
                Objects.equals(dateCommand, command.dateCommand) &&
                Objects.equals(food, command.food) &&
                Objects.equals(beer, command.beer) &&
                Objects.equals(client, command.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommand, dateCommand, food, beer, client);
    }

    @Override
    public String toString() {
        return "Command{" +
                "idCommand='" + idCommand + '\'' +
                ", dateCommand='" + dateCommand + '\'' +
                ", food=" + food +
                ", beer=" + beer +
                ", client='" + client + '\'' +
                '}';
    }
}
