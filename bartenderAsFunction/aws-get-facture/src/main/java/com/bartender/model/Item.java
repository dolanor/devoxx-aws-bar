package com.bartender.model;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Item {
    private String item;
    private int amount;
    private boolean served;

    public static Optional<Item> from(Item newValue) {
        return Optional.ofNullable(newValue)
                .map(newItem ->
                    new Item()
                        .setItem(newItem.item)
                        .setAmount(newItem.amount)
                        .setServed(newItem.served)
                );
    }

    public Map<String, AttributeValue> marshal() {
        final HashMap<String, AttributeValue> map = new HashMap<>();
        map.put("item", AttributeValue.builder().s(this.getItem()).build());
        map.put("amount", AttributeValue.builder().n(String.valueOf(this.getAmount())).build());
        map.put("served", AttributeValue.builder().bool(this.isServed()).build());
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
        Item item1 = (Item) o;
        return amount == item1.amount &&
                served == item1.served &&
                Objects.equals(item, item1.item);
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
