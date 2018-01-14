package br.com.cantinho.things.repository;

import br.com.cantinho.things.model.Thing;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Thing repository to abstract communication to the real repository.
 */
public interface ThingRepository extends ReactiveMongoRepository<Thing, String> {

}
