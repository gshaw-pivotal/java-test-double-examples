package gs.javatestdoubleexamples.services;

import gs.javatestdoubleexamples.models.TheThing;
import gs.javatestdoubleexamples.ports.ThingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class APIServiceTestMockito {

    @Mock
    private ThingRepository repository;

    @InjectMocks
    private APIService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        service = new APIService(repository);
    }

    @Test
    public void test() {
        //These look like stubbing, fixed responses no matter what
        doNothing().when(repository).add(any());
        when(repository.getNumberOfThings()).thenReturn(5);

        //Capturing info, maybe something a spy does?
        ArgumentCaptor<TheThing> thingCaptor = ArgumentCaptor.forClass(TheThing.class);

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

        assertEquals(service.getNumberOfThings(), 5);

        //Looks like mocking as it checking the wiring / integration
        //Or maybe a spy?  There is tracking of how many times the method was called
        verify(repository, atLeast(5)).add(thingCaptor.capture());
        verify(repository, times(1)).getNumberOfThings();

        //Spying?
        assertEquals(expectedThings, thingCaptor.getAllValues());
    }
}
