package com.comake.server.repository;

import com.comake.server.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long>
{
}
