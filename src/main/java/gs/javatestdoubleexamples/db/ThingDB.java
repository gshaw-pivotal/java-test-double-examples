package gs.javatestdoubleexamples.db;

import gs.javatestdoubleexamples.models.TheThing;
import org.springframework.stereotype.Service;

@Service
public class ThingDB {

    //Pretend I connect to a real database that is external and has persistence...
    //I exist so that test double versions can be created and demoed...

    public void add(TheThing thing) {}

    public int getNumberOfThings() { return 0;}
}
