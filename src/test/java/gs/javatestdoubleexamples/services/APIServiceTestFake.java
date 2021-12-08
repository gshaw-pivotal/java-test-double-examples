package gs.javatestdoubleexamples.services;

import gs.javatestdoubleexamples.models.TheThing;
import gs.javatestdoubleexamples.ports.ThingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class APIServiceTestFake {

    private ThingDBFake mock = new ThingDBFake();

    private ThingRepository repository = mock;

    private APIService service;

    @BeforeEach
    public void setup() {
        service = new APIService(repository);
    }

    @Test
    public void test() {
        List<TheThing> expectedThings = new ArrayList<>();
        int numberOfAdds = ThreadLocalRandom
                .current()
                .nextInt(5, 10 + 1);

        System.out.println("Number of adds: " + numberOfAdds);

        for (int i = 0; i < numberOfAdds; i++) {
            TheThing thing = TheThing.builder()
                    .name("foo" + i)
                    .desc("...")
                    .id(UUID.randomUUID())
                    .build();
            expectedThings.add(thing);
            service.addTheThing(thing);
        }

        assertEquals(numberOfAdds, service.getNumberOfThings());

        assertNotNull(service.getTheThing(expectedThings.get(2).getId()));
        assertNull(service.getTheThing(UUID.randomUUID()));
    }

    private class ThingDBFake implements ThingRepository {

        private List<TheThing> actualThings = new ArrayList<>();

        @Override
        public void add(TheThing thing) {
            actualThings.add(thing);
        }

        @Override
        public TheThing getThing(UUID id) {
            List<TheThing> matchingThings = actualThings
                    .stream()
                    .filter(thing -> thing.getId().equals(id))
                    .collect(Collectors.toList());

            if (matchingThings.size() != 1) {
                return null;
            }

            return matchingThings.get(0);
        }

        @Override
        public int getNumberOfThings() {
            return actualThings.size();
        }
    }
}