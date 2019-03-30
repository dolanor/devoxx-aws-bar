package com.bartender.model;

import java.util.Objects;

public class CommandRequest {
    private Item food;
    private Item beer;

    public Item getFood() {
        return food;
    }

    public CommandRequest setFood(Item food) {
        this.food = food;
        return this;
    }

    public Item getBeer() {
        return beer;
    }

    public CommandRequest setBeer(Item beer) {
        this.beer = beer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandRequest)) return false;
        CommandRequest that = (CommandRequest) o;
        return Objects.equals(food, that.food) &&
                Objects.equals(beer, that.beer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food, beer);
    }

    @Override
    public String toString() {
        return "CommandRequest{" +
                "food=" + food +
                ", beer=" + beer +
                '}';
    }
}
