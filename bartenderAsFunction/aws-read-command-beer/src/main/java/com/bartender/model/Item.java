package com.bartender.model;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Item {
    private String item;
    private int amount;
    private boolean served;

    Map<String, AttributeValue> marshal() {
        final HashMap<String, AttributeValue> map = new HashMap<>();
        map.put("Name", AttributeValue.builder().s(this.getItem()).build());
        map.put("Amount", AttributeValue.builder().s(String.valueOf(this.getAmount())).build());
        map.put("Served", AttributeValue.builder().bool(this.isServed()).build());
        return map;
    }

    public String getItem() {
        return item;
    }

    public Item setItem(String item) {
        this.item = item;
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
                Objects.equals(item, item.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, amount, served);
    }

    @Override
    public String toString() {
        return "Item{" +
                "item='" + item + '\'' +
                ", amount=" + amount +
                ", served=" + served +
                '}';
    }

}
