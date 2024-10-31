package org.escuelaing.eci.service.place;

import org.escuelaing.eci.repository.place.Place;
import java.util.List;
import java.util.Optional;

public interface PlaceService {
    Place save(Place place);
    Optional<Place> findById(String id);
    List<Place> all();
    Place deleteById(String id);
    Place update(Place place, String id);
}
