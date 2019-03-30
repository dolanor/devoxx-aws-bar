package com.bartender.dao;

import com.bartender.model.CommandRequest;
import com.bartender.model.CommandResponse;

public interface ChangeBarStatusRepository {
    CommandResponse saveCommand(CommandRequest commandRequest);
}
