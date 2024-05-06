package br.com.personmanagement.api.controller;

import br.com.personmanagement.api.entity.People;
import br.com.personmanagement.service.PeopleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public People createPeople(@RequestBody @Valid People people){


        return peopleService.create(people);
    }



}
