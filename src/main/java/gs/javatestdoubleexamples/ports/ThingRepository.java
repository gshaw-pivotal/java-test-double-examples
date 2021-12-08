package gs.javatestdoubleexamples.ports;

import gs.javatestdoubleexamples.models.TheThing;

public interface ThingRepository {

    void add(TheThing thing);

    int getNumberOfThings();
}
