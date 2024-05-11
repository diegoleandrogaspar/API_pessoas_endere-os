package br.com.personmanagement.domain.service;

import br.com.personmanagement.application.dto.request.PeopleRequestDTO;
import br.com.personmanagement.application.dto.response.PeopleResponseDTO;
import br.com.personmanagement.domain.entity.Address;
import br.com.personmanagement.domain.entity.People;
import br.com.personmanagement.infrastructure.repository.AddressRepository;
import br.com.personmanagement.infrastructure.repository.PeopleRepository;
import br.com.personmanagement.infrastructure.specifications.PeopleSpecifications;
import br.com.personmanagement.web.exception.AddressNotFoundException;
import br.com.personmanagement.web.exception.BusinessException;
import br.com.personmanagement.web.exception.PeopleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    public static final String MSG_PEOPLE_NOT_FOUND
            = "Não existe cadastro de pessoa com o código %d";

    public static final String MSG_ADDRESS_NOT_FOUND
            = "Não existe cadastro de endereço com o código %d ";

    @Autowired
    private AddressService addressService;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<People> getAllPeople(PeopleSpecifications peopleSpecifications) {
        return peopleRepository.findAll(peopleSpecifications);
    }

    @Transactional
    public People create(PeopleRequestDTO peopleRequestDTO) {
        People people = new People();
        people.setName(peopleRequestDTO.getName());
        people.setDateOfBirth(peopleRequestDTO.getDateOfBirth());

        Long addressId = peopleRequestDTO.getAddressId();

        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            people.getAddress().add(address);
            address.setPeople(people);
            return peopleRepository.save(people);
        } else {
            throw new AddressNotFoundException(MSG_ADDRESS_NOT_FOUND + addressId);
        }
    }

    public People searchOrFail(Long peopleId) {
            return peopleRepository.findById(peopleId)
                    .orElseThrow(() -> new PeopleNotFoundException(
                            String.format(MSG_PEOPLE_NOT_FOUND, peopleId)));
        }
    }