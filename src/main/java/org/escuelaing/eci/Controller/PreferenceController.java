package org.escuelaing.eci.Controller;

import org.escuelaing.eci.repository.preference.Preference;
import org.escuelaing.eci.service.preference.PreferenceService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/preference")
@CrossOrigin(origins = "http://localhost:4200")
public class PreferenceController {

    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @GetMapping()
    public ArrayList<Preference> getPreferences() {
        return (ArrayList<Preference>) this.preferenceService.all();
    }

    @PostMapping()
    public Preference savePreference(@RequestBody Preference preference) {
        return this.preferenceService.save(preference);
    }

    @GetMapping("/getPreference/{id}")
    public Optional<Preference> getPreference(@PathVariable String id) {
        return this.preferenceService.findById(id);
    }

    @PostMapping("/create")
    public Preference createPreference(@RequestBody Preference preference) {
        System.out.println("Received request to create preference: " + preference);
        return this.preferenceService.save(preference);
    }

    @PostMapping("/update")
    public Preference updatePreference(@RequestBody Preference preference, @RequestParam String preferenceId) {
        System.out.println("Received request to update preference: " + preference);
        return this.preferenceService.update(preference, preferenceId);
    }

    @GetMapping("/delete/{id}")
    public Preference deletePreference(@PathVariable String id) {
        return this.preferenceService.deleteById(id);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "PreferenceController is working!";
    }
}
