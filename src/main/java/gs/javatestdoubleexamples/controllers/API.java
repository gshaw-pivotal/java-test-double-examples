package gs.javatestdoubleexamples.controllers;

import gs.javatestdoubleexamples.models.TheThing;
import gs.javatestdoubleexamples.services.APIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class API {

    private final APIService service;

    public API(APIService service) {
        this.service = service;
    }

    @PostMapping(value = "/thing")
    public void add(@RequestBody TheThing thing) {
        service.addTheThing(thing);
    }

    @GetMapping(value = "/thing")
    public void get() {
        service.doTheGet();
    }

    @GetMapping(value = "/thing/count")
    public void count() {
        service.getNumberOfThings();
    }
}
