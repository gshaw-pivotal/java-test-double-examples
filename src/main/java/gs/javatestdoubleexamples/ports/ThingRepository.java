package gs.javatestdoubleexamples.ports;

import gs.javatestdoubleexamples.models.TheThing;

import java.util.UUID;

public interface ThingRepository {

    void add(TheThing thing);

    TheThing getThing(UUID id);

    int getNumberOfThings();
}
