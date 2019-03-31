package com.bartender.model;

import software.amazon.awssdk.core.SdkBytes;

import java.util.Objects;
import java.util.Optional;

public class ShadowState {
    private BarStatus barStatus;

    public static ShadowState Closed() {
        final BarStatus barStatus = new BarStatus().setDesired("CLOSED");
        return new ShadowState().setBarStatus(barStatus);
    }

    public Optional<SdkBytes> buildPayload() {
        // TODO 04, call SdkBytes.fromUtf8String after serializing the 'ShadowState'
        return Json.serializer().toJson(this)
                .map(SdkBytes::fromUtf8String);
    }

    public static class BarStatus {
        String desired;

        public String getDesired() {
            return desired;
        }

        public BarStatus setDesired(String desired) {
            this.desired = desired;
            return this;
        }
    }


    public BarStatus getBarStatus() {
        return barStatus;
    }

    public ShadowState setBarStatus(BarStatus barStatus) {
        this.barStatus = barStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShadowState)) return false;
        ShadowState that = (ShadowState) o;
        return Objects.equals(barStatus, that.barStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barStatus);
    }

    @Override
    public String toString() {
        return "ShadowState{" +
                "barStatus=" + barStatus +
                '}';
    }
}
