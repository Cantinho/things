package br.com.cantinho.things.resource;

import br.com.cantinho.things.model.Thing;
import br.com.cantinho.things.model.ThingEvent;
import br.com.cantinho.things.repository.ThingRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Thing resource where all things resources are managed.
 */
@RestController
@RequestMapping("/rest/thing")
public class ThingResource {

  /**
   * Thing repository.
   */
  private final ThingRepository thingRepository;

  /**
   * Builds a custom Thing repository.
   *
   * @param thingRepository @{@link ThingRepository}.
   */
  public ThingResource(final ThingRepository thingRepository) {
    this.thingRepository = thingRepository;
  }

  /**
   * Retrieves all things.
   *
   * @return a publisher of a collection of things.
   */
  @GetMapping("/all")
  public Flux<Thing> getAll() {
    return thingRepository.findAll();
  }

  /**
   * Retrieves a thing by id.
   *
   * @param thingId a string representing a thing id.
   * @return a @{@link Mono<Thing>} object.
   */
  @GetMapping("/{id}")
  public Mono<Thing> getId(@PathVariable("id") final String thingId) {
    return thingRepository.findById(thingId);
  }

  /**
   * Retrieves a thing collection.
   *
   * @param thingId a string representing a thing id.
   * @return a thing collection as @{@link Flux<Thing>}.
   */
  @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ThingEvent> getEvents(@PathVariable("id") final String thingId) {
    return thingRepository.findById(thingId)
        .flatMapMany(thing -> {

          Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
          Flux<ThingEvent> thingEventFlux =
              Flux.fromStream(
                  Stream.generate(() -> new ThingEvent(thing, new Date()))
              );

          return Flux.zip(interval, thingEventFlux)
              .map(Tuple2::getT2);
        });
  }

}
