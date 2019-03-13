package com.bartender.dao;

import com.bartender.model.DrunkClientResponse;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.AttachPolicyResponse;
import software.amazon.awssdk.services.iot.model.AttachThingPrincipalResponse;
import software.amazon.awssdk.services.iot.model.CreateKeysAndCertificateResponse;
import software.amazon.awssdk.services.iot.model.CreateThingResponse;

public interface RegisterClientRepository {
    DrunkClientResponse registerNewDevice(String clientId);

    CreateKeysAndCertificateResponse createKeysAndCertificate(String clientId, IotClient client);

    CreateThingResponse createThing(DrunkClientResponse drunkClientResponse, IotClient client);

    AttachPolicyResponse attachPolicy(DrunkClientResponse drunkClientResponse, IotClient client);

    AttachThingPrincipalResponse attachThingPrincipal(DrunkClientResponse response, IotClient client);
}
