package org.escuelaing.eci.Controller;

import org.escuelaing.eci.repository.place.Place;
import org.escuelaing.eci.service.place.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/place")
@CrossOrigin(origins = "http://localhost:4200")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping()
    public ArrayList<Place> getPlaces() {
        return (ArrayList<Place>) placeService.all();
    }

    @PostMapping()
    public Place savePlace(@RequestBody Place place) {
        return placeService.save(place);
    }

    @GetMapping("/getPlace/{id}")
    public Optional<Place> getPlace(@PathVariable String id) {
        return placeService.findById(id);
    }

    @PutMapping("/update/{id}")
    public Place updatePlace(@RequestBody Place place, @PathVariable String id) {
        return placeService.update(place, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePlace(@PathVariable String id) {
        placeService.deleteById(id);
    }
}
