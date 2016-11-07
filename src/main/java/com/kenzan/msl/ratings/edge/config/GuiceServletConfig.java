package com.kenzan.msl.ratings.edge.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.kenzan.msl.ratings.client.config.RatingsDataClientModule;

/**
 * @author Kenzan
 */
public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    // TODO replace Guice.createInjector for bootstrap LifecycleInjector
    return Guice.createInjector(new RatingsDataClientModule(), new RatingsEdgeModule(),
        new RestModule());
  }
}
