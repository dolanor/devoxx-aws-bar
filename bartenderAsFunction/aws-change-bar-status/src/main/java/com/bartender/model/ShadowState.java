package com.bartender.model;

import software.amazon.awssdk.core.SdkBytes;

import java.util.Objects;
import java.util.Optional;

public class ShadowState {
    private BarState state;

    public static ShadowState Closed() {
        final BarState barState = new BarState().setDesired("CLOSED");
        return new ShadowState().setState(barState);
    }

    public Optional<SdkBytes> buildPayload() {
        // TODO 04, call SdkBytes.fromUtf8String after serializing the 'ShadowState'
        return Json.serializer().toJson(this.state)
                .map(SdkBytes::fromUtf8String);
    }

    public static class BarState {
        String desired;

        public String getDesired() {
            return desired;
        }

        public BarState setDesired(String desired) {
            this.desired = desired;
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
