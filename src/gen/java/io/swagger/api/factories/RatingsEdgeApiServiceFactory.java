package io.swagger.api.factories;

import io.swagger.api.RatingsEdgeApiService;
import io.swagger.api.impl.RatingsEdgeApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2016-01-25T12:48:18.840-06:00")
public class RatingsEdgeApiServiceFactory {

   private final static RatingsEdgeApiService service = new RatingsEdgeApiServiceImpl();

   public static RatingsEdgeApiService getRatingsEdgeApi()
   {
      return service;
   }
}
