package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.AttachPolicyResponse;
import software.amazon.awssdk.services.iot.model.AttachThingPrincipalResponse;
import software.amazon.awssdk.services.iot.model.CreateKeysAndCertificateResponse;
import software.amazon.awssdk.services.iot.model.CreateThingResponse;

public interface ReadCommandFoodRepository {

    CommandResponse saveCommand(Command clientId);

}
