package br.com.personmanagement.service;

import br.com.personmanagement.api.entity.People;
import br.com.personmanagement.repository.PeopleRepository;
import br.com.personmanagement.web.exception.PersonNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeopleService {

    public static final String MSG_PEOPLE_NOT_FOUND
            = "Person not found with ID: %d";

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public People create(People people) {
        return peopleRepository.save(people);
    }

    public People SearchOrFail(Long peopleId) {
        return peopleRepository.findById(peopleId)
                .orElseThrow(() -> new PersonNotFoundException(
                        String.format(MSG_PEOPLE_NOT_FOUND, peopleId)));
    }
}
