package gs.javatestdoubleexamples.services;

import gs.javatestdoubleexamples.models.TheThing;
import gs.javatestdoubleexamples.ports.ThingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

class APIServiceTestSpy {

    private ThingDBSpy spy = new ThingDBSpy();

    private ThingRepository repository = spy;

    private APIService service;

    @BeforeEach
    public void setup() {
        service = new APIService(repository);
    }

    @Test
    public void test() {
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
            service.addTheThing(thing);
        }

//        Uncomment below to break the spy check
//        TheThing thing = TheThing.builder()
//                .name("foo")
//                .desc("...")
//                .id(UUID.randomUUID())
//                .build();
//        service.addTheThing(thing);

        assertEquals(spy.timesAddCalled(), numberOfAdds);

        assertEquals(spy.timesGetNumberCalled(), 0);
        service.getNumberOfThings();
        assertEquals(spy.timesGetNumberCalled(), 1);

        assertEquals(spy.timesGetCalled(), 0);
    }

    private class ThingDBSpy implements ThingRepository {

        private int timesAddCalled = 0;
        private int timesGetCalled = 0;
        private int timesGetNumberCalled = 0;

        private List<TheThing> actualThings = new ArrayList<>();

        @Override
        public void add(TheThing thing) {
            timesAddCalled++;
            actualThings.add(thing);
        }

        @Override
        public TheThing getThing(UUID id) {
            timesGetCalled++;
            return null;
        }

        @Override
        public int getNumberOfThings() {
            timesGetNumberCalled++;
            return 0;
        }

        public int timesAddCalled() {
            return timesAddCalled;
        }

        public int timesGetCalled() {
            return timesGetCalled;
        }

        public int timesGetNumberCalled() {
            return timesGetNumberCalled;
        }
    }
}