package br.com.cantinho.things.repository;

import br.com.cantinho.things.model.Thing;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 *
 */
public interface ThingRepository extends ReactiveMongoRepository<Thing, String> {

}
