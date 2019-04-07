package com.bartender.dao;

import com.bartender.model.Command;

import java.util.List;

public interface GetFactureRepository {
    Command saveCommand(Command clientId);

    List<Command> getCommands(String deviceId);
}
