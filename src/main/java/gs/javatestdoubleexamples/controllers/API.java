package gs.javatestdoubleexamples.controllers;

import gs.javatestdoubleexamples.services.APIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class API {

    private final APIService service;

    public API(APIService service) {
        this.service = service;
    }

    @GetMapping(value = "/get")
    public void get() {
        service.doTheGet();
    }
}
