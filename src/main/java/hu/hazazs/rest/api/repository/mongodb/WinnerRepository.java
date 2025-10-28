package hu.hazazs.rest.api.repository.mongodb;

import hu.hazazs.rest.api.entity.mongodb.Winner;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository extends ReactiveCrudRepository<Winner, String> {

}