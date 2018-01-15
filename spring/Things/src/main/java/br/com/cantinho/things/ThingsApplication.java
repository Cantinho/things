package br.com.cantinho.things;

import br.com.cantinho.things.model.Thing;
import br.com.cantinho.things.repository.ThingRepository;
import br.com.cantinho.things.util.Utils;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static java.util.UUID.*;

/**
 * Start point of Things application.
 */
@SpringBootApplication
@EnableMongoAuditing
public class ThingsApplication {

  /**
   * Here the things come alive.
   *
   * @param args string arguments.
   */
  public static void main(final String[] args) {
    SpringApplication.run(ThingsApplication.class, args);
  }

  /**
   * A @{@link CommandLineRunner} used to mock some data insertions.
   *
   * @param thingRepository a thing repository.
   * @return a @{@link CommandLineRunner}.
   */
  @Bean
  protected CommandLineRunner things(final ThingRepository thingRepository) {
    return args -> thingRepository.deleteAll()
        .subscribe(null, null, () ->
            Stream.of(new Thing(Utils.uuidAsString(randomUUID()), "Horizon", 100),
            new Thing(Utils.uuidAsString(randomUUID()), "PS4", 2400),
            new Thing(Utils.uuidAsString(randomUUID()), "Batman", 100),
            new Thing(Utils.uuidAsString(randomUUID()), "Guitar", 100),
            new Thing(Utils.uuidAsString(randomUUID()), "Hat", 100),
            new Thing(Utils.uuidAsString(randomUUID()), "Smart phone", 100))
            .forEach(thing -> thingRepository//NOPMD
                .save(thing)
                .subscribe(System.out::println)));
  }



}
