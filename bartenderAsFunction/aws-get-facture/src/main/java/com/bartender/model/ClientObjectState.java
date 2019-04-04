package com.bartender.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientObjectState {
    private String barStatus;

    public String getBarStatus() {
        return barStatus;
    }

    public ClientObjectState setBarStatus(String barStatus) {
        this.barStatus = barStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientObjectState)) return false;
        ClientObjectState that = (ClientObjectState) o;
        return Objects.equals(barStatus, that.barStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barStatus);
    }

    @Override
    public String toString() {
        return "ClientObjectState{" +
                "barStatus='" + barStatus + '\'' +
                '}';
    }
}
