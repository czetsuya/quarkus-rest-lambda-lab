package com.czetsuyatech;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/parameters")
public class ParameterStoreController {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getProperties() {

    ParameterStoreClient parameterStoreClient = ParameterStoreClient.create();

    return parameterStoreClient.getParameterStoreProperty("/dev/cx-promotion-service/DB_USERNAME") + ":"
        + parameterStoreClient.getParameterStoreProperty("/dev/cx-promotion-service/DB_PASSWORD");
  }
}
