package org.escuelaing.eci.service.preference;

import org.escuelaing.eci.repository.preference.Preference;
import org.escuelaing.eci.repository.preference.PreferenceMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreferenceServiceMongoDb implements PreferenceService {

    private final PreferenceMongoRepository preferenceMongoRepository;

    @Autowired
    public PreferenceServiceMongoDb(PreferenceMongoRepository preferenceMongoRepository) {
        this.preferenceMongoRepository = preferenceMongoRepository;
    }

    @Override
    public Preference save(Preference preference) {
        return preferenceMongoRepository.save(preference);
    }

    @Override
    public Optional<Preference> findById(String id) {
        return preferenceMongoRepository.findById(id);
    }

    @Override
    public List<Preference> all() {
        return preferenceMongoRepository.findAll();
    }

    @Override
    public Preference deleteById(String id) {
        Optional<Preference> preference = preferenceMongoRepository.findById(id);
        if (preference.isPresent()) {
            preferenceMongoRepository.deleteById(id);
            return preference.get(); // Return the deleted preference
        } else {
            throw new RuntimeException("Preference with ID " + id + " not found");
        }
    }

    @Override
    public Preference update(Preference preference, String preferenceId) {
        return preferenceMongoRepository.findById(preferenceId)
                .map(existingPreference -> {
                    existingPreference.setFoodType(preference.getFoodType());
                    existingPreference.setValue(preference.getValue());
                    return preferenceMongoRepository.save(existingPreference);
                }).orElse(null);
    }
}

