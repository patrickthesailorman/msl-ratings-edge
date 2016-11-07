package com.kenzan.msl.ratings.edge;

import com.google.common.base.Optional;
import com.kenzan.msl.ratings.edge.services.RatingsEdgeServiceImpl;
import io.swagger.api.RatingsEdgeApi;
import io.swagger.api.impl.RatingsEdgeApiOriginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.HashMap;

public class Main {
  /**
   * Runs jetty server to expose jersey API
   * 
   * @param args String array
   * @throws Exception if server doesn't start
   */
  public static void main(String[] args) throws Exception {

    RatingsEdgeServiceImpl.archaiusProperties = new HashMap<String, Optional<String>>();
    RatingsEdgeServiceImpl.archaiusProperties.put("region",
        Optional.fromNullable(System.getProperty("archaius.deployment.region")));
    RatingsEdgeServiceImpl.archaiusProperties.put("domainName",
        Optional.fromNullable(System.getProperty("archaius.deployment.domainName")));

    Server jettyServer = new Server(9004);
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    context.addFilter(RatingsEdgeApiOriginFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
    jerseyServlet.setInitOrder(0);

    jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
        RatingsEdgeApi.class.getCanonicalName());

    try {

      jettyServer.start();
      jettyServer.join();

    } finally {
      jettyServer.destroy();
    }
  }
}
