package com.bartender.model;

import java.util.Objects;

public class DrunkClientRequest {
    private String id;

    public String getId() {
        return id;
    }

    public DrunkClientRequest setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DrunkClientRequest)) return false;
        DrunkClientRequest that = (DrunkClientRequest) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DrunkClientRequest{" +
                "id='" + id + '\'' +
                '}';
    }
}
