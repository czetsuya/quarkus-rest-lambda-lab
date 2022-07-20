package com.czetsuyatech;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.providers.AwsRegionProvider;
import software.amazon.awssdk.regions.providers.SystemSettingsRegionProvider;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;

public class ParameterStoreClient {

  static SsmClient ssmClient;

  public ParameterStoreClient() {

    final AwsCredentialsProviderChain awsCredentialsProviderChain = AwsCredentialsProviderChain
        .of(EnvironmentVariableCredentialsProvider.create());
    final AwsRegionProvider awsRegionProvider = new SystemSettingsRegionProvider();

    ssmClient = SsmClient.builder()
        .credentialsProvider(awsCredentialsProviderChain)
        .region(awsRegionProvider.getRegion())
        .build();
  }

  public static ParameterStoreClient create() {
    return new ParameterStoreClient();
  }

  public String getParameterStoreProperty(String propertyName) {

    return ssmClient
        .getParameter(GetParameterRequest.builder()
            .name(propertyName)
            .withDecryption(true)
            .build())
        .parameter()
        .value();
  }
}
