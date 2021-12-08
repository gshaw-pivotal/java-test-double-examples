package gs.javatestdoubleexamples.services;

import gs.javatestdoubleexamples.models.TheThing;
import gs.javatestdoubleexamples.ports.ThingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class APIService {

    private final ThingRepository db;

    public APIService(ThingRepository db) {
        this.db = db;
    }

    public void addTheThing(TheThing thing){ db.add(thing); }

    public TheThing getTheThing(UUID id) {
        return db.getThing(id);
    }
    
    public int getNumberOfThings() { return db.getNumberOfThings(); }
}
