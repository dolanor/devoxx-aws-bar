package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;

public interface ChangeBarStatusRepository {
    CommandResponse saveCommand(Command clientId);
}
