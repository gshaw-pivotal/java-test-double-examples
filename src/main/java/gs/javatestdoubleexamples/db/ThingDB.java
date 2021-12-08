package gs.javatestdoubleexamples.db;

import gs.javatestdoubleexamples.models.TheThing;
import gs.javatestdoubleexamples.ports.ThingRepository;
import org.springframework.stereotype.Service;

@Service
public class ThingDB implements ThingRepository {

    //Pretend I connect to a real database that is external and has persistence...
    //I exist so that the app can actually run...

    public void add(TheThing thing) {}

    public int getNumberOfThings() { return 0;}
}
