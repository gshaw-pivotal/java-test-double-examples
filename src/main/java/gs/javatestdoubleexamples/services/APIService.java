package gs.javatestdoubleexamples.services;

import gs.javatestdoubleexamples.db.ThingDB;
import gs.javatestdoubleexamples.models.TheThing;
import org.springframework.stereotype.Service;

@Service
public class APIService {

    private final ThingDB db;

    public APIService(ThingDB db) {
        this.db = db;
    }

    public void addTheThing(TheThing thing){
    }

    public void doTheGet() {
    }
}
