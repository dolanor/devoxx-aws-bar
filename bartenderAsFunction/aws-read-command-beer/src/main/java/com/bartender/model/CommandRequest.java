package com.bartender.model;

import java.util.Objects;

public class CommandRequest {
    private Item beer;

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
        return Objects.equals(beer, that.beer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beer);
    }

    @Override
    public String toString() {
        return "CommandRequest{" +
                "beer=" + beer +
                '}';
    }
}
