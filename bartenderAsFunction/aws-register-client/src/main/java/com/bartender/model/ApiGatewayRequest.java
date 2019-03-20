package com.bartender.model;

import java.io.Serializable;
import java.util.Objects;

public class ApiGatewayRequest implements Serializable {
    private String body;

    public String getBody() {
        return body;
    }

    public ApiGatewayRequest setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiGatewayRequest)) return false;
        ApiGatewayRequest that = (ApiGatewayRequest) o;
        return Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    @Override
    public String toString() {
        return "ApiGatewayRequest{" +
                "body='" + body + '\'' +
                '}';
    }
}
