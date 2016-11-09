package com.kenzan.msl.ratings.edge.config;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.kenzan.msl.ratings.client.config.RatingsDataClientModule;
import com.netflix.governator.guice.LifecycleInjector;

/**
 * @author Kenzan
 */
public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return LifecycleInjector.builder()
            .withModules(
                    new RatingsDataClientModule(), new RatingsEdgeModule(),
                    new RestModule())
            .build()
            .createInjector();
  }
}
