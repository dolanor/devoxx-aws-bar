package com.bartender.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IotShadowDoc {
    private int version;
    private IotShadowState state;

    public int getVersion() {
        return version;
    }

    public IotShadowDoc setVersion(int version) {
        this.version = version;
        return this;
    }

    public IotShadowState getState() {
        return state;
    }

    public IotShadowDoc setState(IotShadowState state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IotShadowDoc)) return false;
        IotShadowDoc that = (IotShadowDoc) o;
        return version == that.version &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, state);
    }

    @Override
    public String toString() {
        return "IotShadowDoc{" +
                "version=" + version +
                ", state=" + state +
                '}';
    }
}
