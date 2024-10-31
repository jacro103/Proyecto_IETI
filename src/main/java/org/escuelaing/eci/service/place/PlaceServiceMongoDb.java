package org.escuelaing.eci.service.place;

import org.escuelaing.eci.repository.place.Place;
import org.escuelaing.eci.repository.place.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceServiceMongoDb implements PlaceService {
    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceMongoDb(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place save(Place place) {
        return placeRepository.save(place);
    }

    @Override
    public Optional<Place> findById(String id) {
        return placeRepository.findById(id);
    }

    @Override
    public List<Place> all() {
        return placeRepository.findAll();
    }

    @Override
    public Place deleteById(String id) {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isPresent()) {
            placeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Place with ID " + id + " not found");
        }
        return null;
    }

    @Override
    public Place update(Place place, String id) {
        return placeRepository.findById(id)
                .map(existingPlace -> {
                    existingPlace.setName(place.getName());
                    existingPlace.setDescription(place.getDescription());
                    existingPlace.setFoodType(place.getFoodType());
                    existingPlace.setValue(place.getValue());
                    existingPlace.setLocation(place.getLocation());
                    existingPlace.setRating(place.getRating());
                    return placeRepository.save(existingPlace);
                }).orElse(null);
    }
}
