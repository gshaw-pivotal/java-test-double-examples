package gs.javatestdoubleexamples.services;

import gs.javatestdoubleexamples.models.TheThing;
import gs.javatestdoubleexamples.ports.ThingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertTrue;

class APIServiceTestStub {

    private int recordCount = 20;

    private ThingRepository repository = new ThingDBStub(recordCount);

    private APIService service;

    @BeforeEach
    public void setup() {
        service = new APIService(repository);
    }

    @Test
    public void test() {
        TheThing thing;
        int numberOfAdds = ThreadLocalRandom
                .current()
                .nextInt(5, 10 + 1);

        System.out.println("Number of adds: " + numberOfAdds);

        for (int i = 0; i < numberOfAdds; i++) {
            thing = TheThing.builder()
                    .name("foo" + i)
                    .desc("...")
                    .id(UUID.randomUUID())
                    .build();
            service.addTheThing(thing);
        }

        assertTrue(service.getNumberOfThings() == recordCount);
    }

    private class ThingDBStub implements ThingRepository {

        private int numberOfRecords;

        public ThingDBStub(int numberOfRecords) {
            this.numberOfRecords = numberOfRecords;
        }

        @Override
        public void add(TheThing thing) { }

        @Override
        public TheThing getThing(UUID id) {
            return TheThing.builder()
                    .name("foo-bar")
                    .desc("lol")
                    .id(UUID.randomUUID())
                    .build();
        }

        @Override
        public int getNumberOfThings() {
            return numberOfRecords;
        }
    }
}