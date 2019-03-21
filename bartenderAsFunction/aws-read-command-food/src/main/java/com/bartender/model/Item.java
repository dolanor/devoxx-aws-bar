package com.bartender.model;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Item {
    private String name;
    private int amount;
    private boolean served;

    Map<String, AttributeValue> marshal() {
        final HashMap<String, AttributeValue> map = new HashMap<>();
        map.put("Name", AttributeValue.builder().s(this.getName()).build());
        map.put("Amount", AttributeValue.builder().s(String.valueOf(this.getAmount())).build());
        map.put("Served", AttributeValue.builder().bool(this.isServed()).build());
        return map;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Item setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public boolean isServed() {
        return served;
    }

    public Item setServed(boolean served) {
        this.served = served;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return amount == item.amount &&
                served == item.served &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, served);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", served=" + served +
                '}';
    }

}
