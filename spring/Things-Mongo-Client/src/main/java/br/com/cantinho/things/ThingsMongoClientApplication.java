package br.com.cantinho.things;

import br.com.cantinho.things.model.Thing;
import br.com.cantinho.things.model.ThingEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * Start point of Things application.
 */
@SpringBootApplication
@EnableMongoAuditing
public class ThingsMongoClientApplication {

  /**
   * Here the things come alive.
   *
   * @param args string arguments.
   */
  public static void main(final String[] args) {
    SpringApplication.run(ThingsMongoClientApplication.class, args);
  }

  @Bean
  WebClient webClient() {
    return WebClient.create("http://localhost:8082/rest/thing");
  }


  @Bean
  protected CommandLineRunner commandLineRunner(final WebClient webClient) {
    return strings -> {
      webClient
          .get()
          .uri("/all")
          .retrieve()
          .bodyToFlux(Thing.class)
          .filter(thing -> "Horizon".equals(thing.getName()))
          .flatMap(thing -> {
            return webClient.get()
                .uri("/{id}/events", thing.getId())
                .retrieve()
                .bodyToFlux(ThingEvent.class);
          })
          .subscribe(System.out::println);
      for(int i = 0; i < 1000; i++) {
        System.out.println("Blocking 1");
        Thread.sleep(1000);
      }
    };

  }



}
