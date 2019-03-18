package com.bartender.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.*;

import com.bartender.model.DrunkClientResponse;
import software.amazon.awssdk.utils.StringUtils;

/**
 * Some examples are specified here
 * https://github.com/aws/aws-sdk-java-v2/blob/master/services/iot/src/it/java/software/amazon/awssdk/services/iot/IotControlPlaneIntegrationTest.java
 */
public class RegisterClientRepositoryImpl implements RegisterClientRepository {

    private static final Logger LOG = LogManager.getLogger(RegisterClientRepositoryImpl.class);

    private final String policyDrunkClient = System.getenv("POLICY_DRUNK_CLIENT");

    @Override
    public DrunkClientResponse registerNewDevice(String clientId) {
        LOG.info("registering a new client with id " + clientId);
        try (IotClient client = newClient()) {
            final CreateKeysAndCertificateResponse keysAndCertificate = createKeysAndCertificate(clientId, client);
            DrunkClientResponse response = buildDrunkClientResponse(keysAndCertificate, clientId);
            createThing(response, client);
            attachPolicy(response, client);
            attachThingPrincipal(response, client);
            return response;
        }
    }

    private DrunkClientResponse buildDrunkClientResponse(CreateKeysAndCertificateResponse createResult, String clientId) {
        LOG.info("buildDrunkClientResponse for client {}", clientId);
        return DrunkClientResponse.builder()
                .setIdClient(clientId)
                .setCertificateArn(createResult.certificateArn())
                .setPublicKey(createResult.keyPair().publicKey())
                .setPrivateKey(createResult.keyPair().privateKey())
                .setCertificatePem(createResult.certificatePem())
                .build();
    }

    public CreateKeysAndCertificateResponse createKeysAndCertificate(String clientId, IotClient client) {
        LOG.info("CreateKeysAndCertificate for client {}", clientId);
        final CreateKeysAndCertificateRequest createReq =
                CreateKeysAndCertificateRequest.builder().setAsActive(true).build();
        return client.createKeysAndCertificate(createReq);
    }

    public CreateThingResponse createThing(DrunkClientResponse response, IotClient client) {
        LOG.info("createThing for client {}", response.getIdClient());
        final CreateThingRequest createThing = CreateThingRequest.builder()
                .thingName(response.getIdClient())
                .build();
        return client.createThing(createThing);
    }

    public AttachPolicyResponse attachPolicy(DrunkClientResponse response, IotClient client) {
        LOG.info("attachPolicy for client {}", response.getIdClient());
        final AttachPolicyRequest request = AttachPolicyRequest.builder()
                .policyName(policyDrunkClient)
                .target(response.getCertificateArn())
                .build();
        return client.attachPolicy(request);
    }

    public AttachThingPrincipalResponse attachThingPrincipal(DrunkClientResponse response, IotClient client) {
        LOG.info("attachThingPrincipal for client {}", response.getIdClient());
        if (StringUtils.isNotBlank(response.getCertificateArn())) {
            final AttachThingPrincipalRequest request = AttachThingPrincipalRequest.builder()
                .thingName(response.getIdClient())
                .principal(response.getCertificateArn())
                .build();
            return client.attachThingPrincipal(request);
        }
        return null;
    }

    private IotClient newClient() {
        return IotClient.builder()
                .region(Region.EU_WEST_1)
                .build();
    }
}
