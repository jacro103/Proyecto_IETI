package org.escuelaing.eci.service.Location;

import org.escuelaing.eci.repository.location.LocationA;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    LocationA save(LocationA location);

    Optional<LocationA> findById(String id);

    List<LocationA> all();

    LocationA deleteById(String id);

    LocationA update(LocationA location, String id);
}
