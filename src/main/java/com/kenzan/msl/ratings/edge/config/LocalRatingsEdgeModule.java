package com.kenzan.msl.ratings.edge.config;

import com.google.inject.AbstractModule;
import com.kenzan.msl.ratings.edge.services.RatingsEdgeService;
import com.kenzan.msl.ratings.edge.services.RatingsEdgeServiceImpl;
import com.netflix.governator.guice.lazy.LazySingletonScope;
import io.swagger.api.RatingsEdgeApiService;
import io.swagger.api.factories.RatingsEdgeApiServiceFactory;
import io.swagger.api.impl.RatingsEdgeApiServiceImpl;

/**
 * @author Kenzan
 */
public class LocalRatingsEdgeModule extends AbstractModule {
  @Override
  public void configure() {
    requestStaticInjection(RatingsEdgeApiServiceFactory.class);
    bind(RatingsEdgeService.class).to(RatingsEdgeServiceImpl.class).in(LazySingletonScope.get());
    bind(RatingsEdgeApiService.class).to(RatingsEdgeApiServiceImpl.class).in(
        LazySingletonScope.get());
  }
}
