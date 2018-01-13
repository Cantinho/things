package br.com.cantinho.things.resource;

import br.com.cantinho.things.model.Thing;
import br.com.cantinho.things.repository.ThingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Thing resource where all things resources are managed.
 */
@RestController
@RequestMapping("/rest/thing")
public class ThingResource {

  private final ThingRepository thingRepository;

  public ThingResource(ThingRepository thingRepository) {
    this.thingRepository = thingRepository;
  }

  @GetMapping("/all")
  public Flux<Thing> getAll() {
    return thingRepository.findAll();
  }

}
