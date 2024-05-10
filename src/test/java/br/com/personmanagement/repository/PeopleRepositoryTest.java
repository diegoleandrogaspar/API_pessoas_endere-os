package br.com.personmanagement.repository;

import br.com.personmanagement.domain.entity.People;
import br.com.personmanagement.infrastructure.repository.PeopleRepository;
import br.com.personmanagement.infrastructure.specifications.PeopleSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    private People people;

    @BeforeEach
    public void setup(){
        //Given
        this.people = new People();
        this.people.setName("Marcio");
        this.people.setDateOfBirth(LocalDate.parse("1991-10-15"));
    }

    @DisplayName("Given People Object when save then Return Saved People")
    @Test
    void testGivenPeopleObject_whenSave_thenReturnSavedPeople() {
        // When
        People savedPeople = peopleRepository.save(this.people);

        // Then
        assertNotNull(savedPeople.getId(), "Saved Person should have an ID after saving");
        assertEquals("Marcio", savedPeople.getName(), "Saved Person should have the correct name");
        assertEquals(LocalDate.parse("1991-10-15"), savedPeople.getDateOfBirth(), "Saved Person should have the correct date of birth");
    }

    @DisplayName("Given People List when findAll then Return People List")
    @Test
    void testGivenPeopleList_whenFindAll_thenReturnPeopleList() {
        // Given
        People people1 = new People();
        people1.setName("Messi");
        people1.setDateOfBirth(LocalDate.parse("1885-09-05"));

        peopleRepository.save(this.people);
        peopleRepository.save(people1);

        //When
        List<People> peopleList = peopleRepository.findAll();

        //Then
        assertNotNull(peopleList);
        assertEquals(2, peopleList.size());
    }

    @DisplayName("Given People Object when findByID then Return People Object")
    @Test
    void testGivenPeopleObject_whenFindById_thenReturnPeopleObject() {
        // Given
        peopleRepository.save(this.people);

        // When
        People savedPerson = peopleRepository.findById(people.getId()).get();

        // Then
        assertNotNull(savedPerson);
        assertEquals(savedPerson.getId(), savedPerson.getId());
    }

    @DisplayName("Given People Object when Specification findByName then Return name People")
    @Test
    void testFindPeopleByName() {
        //Given
        String nameToSearch = "Diego";
        People johnDoe = new People();
        johnDoe.setName("Diego Leandro");
        peopleRepository.save(johnDoe);

        // When
        Pageable pageable = PageRequest.of(0, 10); // Página 0, com 10 elementos por página
        PeopleSpecifications spec = new PeopleSpecifications(nameToSearch);
        Page<People> peoplePage = peopleRepository.findAll(spec, pageable);

        // Then
        assertTrue(peoplePage.hasContent()); // Verifica se há conteúdo na página
        assertEquals(1, peoplePage.getTotalElements()); // Deve haver apenas 1 elemento na página
        assertEquals("Diego Leandro", peoplePage.getContent().get(0).getName());
    }

    @DisplayName("test Given People Object when Update People then Return Update People")
    @Test
    void testGivenPeopleObject_whenUpdatePeople_thenReturnUpdatePeople() {
        // Given
        peopleRepository.save(this.people);

        // When
        People savedPeople = peopleRepository.findById(people.getId()).get();
        savedPeople.setName("Diego");
        savedPeople.setDateOfBirth(LocalDate.parse("1990-12-11"));

        People updatedPeople = peopleRepository.save(savedPeople);

        // Then
        assertNotNull(updatedPeople);
        assertEquals("Diego", updatedPeople.getName());
        assertEquals(LocalDate.parse("1990-12-11" ), updatedPeople.getDateOfBirth());
    }
}
