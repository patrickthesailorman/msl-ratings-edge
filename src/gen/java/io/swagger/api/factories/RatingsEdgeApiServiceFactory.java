package io.swagger.api.factories;

import com.google.inject.Inject;
import io.swagger.api.RatingsEdgeApiService;
import io.swagger.api.impl.RatingsEdgeApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JaxRSServerCodegen", date = "2016-01-25T12:48:18.840-06:00")
public class RatingsEdgeApiServiceFactory {

   @Inject
   public static RatingsEdgeApiService service;

   public static RatingsEdgeApiService getRatingsEdgeApi()
   {
      return service;
   }
}
