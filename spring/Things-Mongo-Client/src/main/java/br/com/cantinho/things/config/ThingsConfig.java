package br.com.cantinho.things.config;

import br.com.cantinho.things.RouterHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

/**
 * Thing configuration where all things routes come in.
 */
@Configuration
public class ThingsConfig {

  /**
   * Routes incoming requests to handler functions.
   *
   * @param routerHandlers @{@link RouterHandlers}.
   * @return a @{@link RouterFunction} to handle the current incoming request that  matches to an
   *     specific predicate.
   */
  @Bean
  RouterFunction<?> routerFunction(RouterHandlers routerHandlers) {
    return RouterFunctions.route(
        RequestPredicates.GET("/rest/thing/all"), routerHandlers::getAll)
        .andRoute(RequestPredicates.GET("/rest/thing/{id}"), routerHandlers::getId)
        .andRoute(RequestPredicates.GET("/rest/thing/{id}/events"), routerHandlers::getEvents
        );
  }
}
