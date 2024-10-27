package org.escuelaing.eci.service.Location;

import org.escuelaing.eci.repository.location.LocationA;
import org.escuelaing.eci.repository.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceMongoDb implements LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceMongoDb(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationA save(LocationA location) {
        return locationRepository.save(location);
    }

    @Override
    public Optional<LocationA> findById(String id) {
        return locationRepository.findById(id);
    }

    @Override
    public List<LocationA> all() {
        return locationRepository.findAll();
    }

    @Override
    public LocationA deleteById(String id) {
        Optional<LocationA> locationA = locationRepository.findById(id);
        if (locationA.isPresent()) {
            locationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Location with ID " + id + " not found");
        }
        return null;
    }

    @Override
    public LocationA update(LocationA location, String locationId) {
        return locationRepository.findById(locationId)
                .map(existingLocation -> {
                    existingLocation.setLat(location.getLat());
                    existingLocation.setLon(location.getLon());
                    existingLocation.setAddress(location.getAddress());
                    return locationRepository.save(existingLocation);
                }).orElse(null);
    }
    
}
