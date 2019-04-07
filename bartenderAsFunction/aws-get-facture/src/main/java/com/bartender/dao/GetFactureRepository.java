package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;

import java.util.List;

public interface GetFactureRepository {
    CommandResponse saveCommand(Command clientId);

    List<CommandResponse> getCommands(String deviceId);
}
