package br.com.personmanagement.services;

import br.com.personmanagement.application.dto.request.PeopleRequestDTO;
import br.com.personmanagement.domain.entity.Address;
import br.com.personmanagement.domain.entity.People;
import br.com.personmanagement.domain.service.AddressService;
import br.com.personmanagement.domain.service.PeopleService;
import br.com.personmanagement.infrastructure.repository.AddressRepository;
import br.com.personmanagement.infrastructure.repository.PeopleRepository;
import br.com.personmanagement.web.exception.PeopleNotFoundException;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PeopleServicesTest {

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private PeopleService peopleService;

    private PeopleRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        this.requestDTO = new PeopleRequestDTO();
        this.requestDTO.setName("Diego");
        this.requestDTO.setDateOfBirth(LocalDate.of(1990,5, 15));
        this.requestDTO.setAddressId(1L);
    }

    @DisplayName("Test Given method Created People in PeopleService")
    @Test
    void testCreatePeople(){
        //Given
        Address mockAddress = new Address();
        mockAddress.setId(1L);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(mockAddress));
        when(peopleRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        //When
        People createdPeople = peopleService.create(this.requestDTO);

        //Then
        assertNotNull(createdPeople);
        assertEquals("Diego", createdPeople.getName());
        assertEquals(LocalDate.of(1990, 5, 15), createdPeople.getDateOfBirth());
        assertEquals(mockAddress.getId(), createdPeople.getAddress().get(0).getId());
    }

    @DisplayName("Test Given invalid ID when searching for People then throw PeopleNotFoundException")
    @Test
    void testSearchOrFailInvalidId() {
        // Given
        Long invalidPeopleId = 999L;

        // Mocking the repository to return empty Optional, simulating not finding the People
        when(peopleRepository.findById(invalidPeopleId)).thenReturn(Optional.empty());

        // When and Then
        Assertions.assertThrows(PeopleNotFoundException.class, () -> {
            peopleService.searchOrFail(invalidPeopleId);
        });
    }


    }


