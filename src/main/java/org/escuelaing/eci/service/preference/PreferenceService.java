package org.escuelaing.eci.service.preference;

import java.util.List;
import java.util.Optional;

import org.escuelaing.eci.repository.preference.Preference;

public interface PreferenceService {
    
    Preference save(Preference preference);

    Optional<Preference> findById(String id);

    List<Preference> all();

    Preference deleteById(String id);

    Preference update(Preference preference, String preferenceId);
}
