package hu.hazazs.rest.api.repository.mongodb;

import hu.hazazs.rest.api.entity.mongodb.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, String> {

}