package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;

public interface GetFactureRepository {
    CommandResponse saveCommand(Command clientId);
}
