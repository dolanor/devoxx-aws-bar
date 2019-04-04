package com.bartender.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IotEventRequest {
    private IotShadowDoc previous;
    private IotShadowDoc current;
    private int timestamp;
    private String deviceId;

    public IotShadowDoc getPrevious() {
        return previous;
    }

    public IotEventRequest setPrevious(IotShadowDoc previous) {
        this.previous = previous;
        return this;
    }

    public IotShadowDoc getCurrent() {
        return current;
    }

    public IotEventRequest setCurrent(IotShadowDoc current) {
        this.current = current;
        return this;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public IotEventRequest setTimestamp(int timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public IotEventRequest setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IotEventRequest)) return false;
        IotEventRequest that = (IotEventRequest) o;
        return timestamp == that.timestamp &&
                Objects.equals(previous, that.previous) &&
                Objects.equals(current, that.current) &&
                Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previous, current, timestamp, deviceId);
    }

    @Override
    public String toString() {
        return "IotEventRequest{" +
                "previous=" + previous +
                ", current=" + current +
                ", timestamp=" + timestamp +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
