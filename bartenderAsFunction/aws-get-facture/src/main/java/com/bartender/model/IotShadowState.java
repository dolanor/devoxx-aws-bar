package com.bartender.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IotShadowState {
    private ClientObjectState desired;
    private ClientObjectState reported;

    public ClientObjectState getDesired() {
        return desired;
    }

    public IotShadowState setDesired(ClientObjectState desired) {
        this.desired = desired;
        return this;
    }

    public ClientObjectState getReported() {
        return reported;
    }

    public IotShadowState setReported(ClientObjectState reported) {
        this.reported = reported;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IotShadowState)) return false;
        IotShadowState that = (IotShadowState) o;
        return Objects.equals(desired, that.desired) &&
                Objects.equals(reported, that.reported);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desired, reported);
    }

    @Override
    public String toString() {
        return "IotShadowState{" +
                "desired=" + desired +
                ", reported=" + reported +
                '}';
    }
}
