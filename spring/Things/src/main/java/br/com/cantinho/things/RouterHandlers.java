package br.com.cantinho.things;

import br.com.cantinho.things.model.Thing;
import br.com.cantinho.things.model.ThingEvent;
import br.com.cantinho.things.repository.ThingRepository;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

/**
 * Represents the functions that handle incoming requests and generate responses.
 */
@Component
public class RouterHandlers {

  /**
   * Thing repository.
   */
  private transient final ThingRepository thingRepository;

  /**
   * Builds a custom @{@link RouterHandlers} object with a thing repository.
   * @param thingRepository a @{@link ThingRepository}.
   */
  public RouterHandlers(final ThingRepository thingRepository) {
    this.thingRepository = thingRepository;
  }

  /**
   * Retrieves all things.
   *
   * @param request a incoming request (@{@link ServerRequest}).
   * @return a @{@link Mono} server response.
   */
  public Mono<ServerResponse> getAll(final ServerRequest request) {
    return ServerResponse.ok().body(thingRepository.findAll(), Thing.class);//NOPMD
  }

  /**
   * Retrieves a thing by id.
   *
   * @param request a incoming request (@{@link ServerRequest}).
   * @return a @{@link Mono} server response.
   */
  public Mono<ServerResponse> getId(final ServerRequest request) {
    return ServerResponse//NOPMD
        .ok()
        .body(
            thingRepository.findById(request.pathVariable("id")), Thing.class
        );
  }

  /**
   * Retrieves a thing event collection.
   *
   * @param request a incoming request (@{@link ServerRequest}).
   * @return a @{@link Mono} server response.
   */
  public Mono<ServerResponse> getEvents(final ServerRequest request) {
    return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(
            thingRepository.findById(request.pathVariable("id"))
            .flatMapMany((Thing thing) -> {
              final Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
              final Flux<ThingEvent> thingEventFlux =
                  Flux.fromStream(
                      Stream.generate(() -> new ThingEvent(thing, new Date()))
                  );
              return Flux.zip(interval, thingEventFlux)//NOPMD//NOPMD
                  .map(Tuple2::getT2);
            }), ThingEvent.class
        );
  }
}
