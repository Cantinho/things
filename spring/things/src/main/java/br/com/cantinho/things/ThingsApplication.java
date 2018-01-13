package br.com.cantinho.things;

import br.com.cantinho.things.model.Thing;
import br.com.cantinho.things.repository.ThingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

/**
 * Start point of Things application.
 */
@SpringBootApplication
public class ThingsApplication {

  @Bean
  CommandLineRunner things(final ThingRepository thingRepository) {
    return args -> {
      thingRepository.deleteAll()
          .subscribe(null, null, () -> {
            Stream.of(new Thing(UUID.randomUUID().toString(), "Horizon", 100),
                new Thing(UUID.randomUUID().toString(), "PS4", 2400),
                new Thing(UUID.randomUUID().toString(), "Horizon", 100),
                new Thing(UUID.randomUUID().toString(), "Horizon", 100),
                new Thing(UUID.randomUUID().toString(), "Horizon", 100),
                new Thing(UUID.randomUUID().toString(), "Horizon", 100))
                .forEach(thing -> {
                  thingRepository
                      .save(thing)
                      .subscribe(saved -> System.out.println(saved));
                });
          });
    };
  }
  /**
   * Here the things come alive.
   *
   * @param args string arguments.
   */
  public static void main(final String[] args) {
    SpringApplication.run(ThingsApplication.class, args);
  }
}
