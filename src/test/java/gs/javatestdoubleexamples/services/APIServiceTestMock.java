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

class APIServiceTestMock {

    private ThingDBMock mock = new ThingDBMock();

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

//        Uncomment below to break the verify
//        TheThing thing = TheThing.builder()
//                .name("foo")
//                .desc("...")
//                .id(UUID.randomUUID())
//                .build();
//        service.addTheThing(thing);

        mock.verify(expectedThings);
    }

    private class ThingDBMock implements ThingRepository {

        private List<TheThing> actualThings = new ArrayList<>();

        @Override
        public void add(TheThing thing) {
            actualThings.add(thing);
        }

        @Override
        public TheThing getThing(UUID id) {
            return null;
        }

        @Override
        public int getNumberOfThings() {
            return 0;
        }

        public void verify(List<TheThing> expectedThings) {
            assertEquals(actualThings, expectedThings);
        }
    }
}