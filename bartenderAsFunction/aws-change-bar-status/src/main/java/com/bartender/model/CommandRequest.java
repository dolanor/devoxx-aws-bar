package com.bartender.model;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandRequest {
    private String userId;

    private CommandRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public static Optional<CommandRequest> from(String path) {
        final String regex = "\\/client\\/(\\w+)\\/close";
        final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(path);

        // TODO if the pattern matches, return the group 1
        return (matcher.find())
            ? Optional.of(new CommandRequest(matcher.group(1)))
            : Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandRequest)) return false;
        CommandRequest that = (CommandRequest) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "CommandRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
