package com.bartender.dao;

import com.bartender.model.Command;
import com.bartender.model.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.iot.IotClient;
import software.amazon.awssdk.services.iot.model.*;

import software.amazon.awssdk.utils.StringUtils;

/**
 * Some examples are specified here
 * https://github.com/aws/aws-sdk-java-v2/blob/master/services/iot/src/it/java/software/amazon/awssdk/services/iot/IotControlPlaneIntegrationTest.java
 */
public class ReadCommandFoodRepositoryImpl implements ReadCommandFoodRepository {

    @Override
    public CommandResponse saveCommand(Command clientId) {
        return null;
    }
}
