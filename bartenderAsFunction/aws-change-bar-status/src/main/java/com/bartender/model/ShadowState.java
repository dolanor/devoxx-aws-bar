package com.bartender.model;

import software.amazon.awssdk.core.SdkBytes;

import java.util.Objects;
import java.util.Optional;

public class ShadowState {
    private BarState state;

    public static ShadowState Closed() {
        final BarState barState = new BarState().setDesired(Desired.CLOSED);
        return new ShadowState().setState(barState);
    }

    public Optional<SdkBytes> buildPayload() {
        // TODO call SdkBytes.fromUtf8String after serializing the 'ShadowState'
        return Json.serializer().toJson(this)
                .map(SdkBytes::fromUtf8String);
    }

    public static class BarState {
        Desired desired;

        public Desired getDesired() {
            return desired;
        }

        public BarState setDesired(Desired desired) {
            this.desired = desired;
            return this;
        }
    }

    public static class Desired {
        public static final Desired CLOSED = new Desired().setBarStatus("CLOSED");
        public static final Desired OPENED = new Desired().setBarStatus("OPENED");

        String barStatus;

        public String getBarStatus() {
            return barStatus;
        }

        public Desired setBarStatus(String barStatus) {
            this.barStatus = barStatus;
            return this;
        }
    }

    public BarState getState() {
        return state;
    }

    public ShadowState setState(BarState state) {
        this.state = state;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShadowState)) return false;
        ShadowState that = (ShadowState) o;
        return Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return "ShadowState{" +
                "state=" + state +
                '}';
    }
}
