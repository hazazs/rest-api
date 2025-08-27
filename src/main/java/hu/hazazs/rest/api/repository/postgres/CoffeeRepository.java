package hu.hazazs.rest.api.repository.postgres;

import hu.hazazs.rest.api.entity.postgres.Coffee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, String> {



}