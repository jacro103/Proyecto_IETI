package org.escuelaing.eci.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.escuelaing.eci.repository.location.LocationA;
import org.escuelaing.eci.service.Location.LocationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping()
    public ArrayList<LocationA> getLocations() {
        return (ArrayList<LocationA>) this.locationService.all();
    }

    @PostMapping()
    public LocationA saveLocation(@RequestBody LocationA location) {
        return locationService.save(location);
    }

    @GetMapping("/getLocation/{id}")
    public Optional<LocationA> getLocation(@PathVariable String id) {
        return locationService.findById(id);
    }

    @PostMapping("/create")
    public LocationA createLocation(@RequestBody LocationA location) {
        return locationService.save(location);
    }

    @PutMapping("/update/{id}")
    public LocationA updateLocation(@RequestBody LocationA location, @PathVariable String id) {
        return locationService.update(location, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLocation(@PathVariable String id) {
        locationService.deleteById(id);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Location Controller is working!";
    }
}
