package hu.hazazs.rest.api.repository.h2;

import hu.hazazs.rest.api.entity.h2.Aircraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {



}