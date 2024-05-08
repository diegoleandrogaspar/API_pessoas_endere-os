package br.com.personmanagement.domain.service;

import br.com.personmanagement.application.dto.request.PeopleRequestDTO;
import br.com.personmanagement.application.dto.response.PeopleResponseDTO;
import br.com.personmanagement.domain.entity.Address;
import br.com.personmanagement.domain.entity.People;
import br.com.personmanagement.infrastructure.repository.AddressRepository;
import br.com.personmanagement.infrastructure.repository.PeopleRepository;
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
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
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

    @Transactional
        public People update(Long peopleId, PeopleRequestDTO peopleRequestDTO) {
            People existingPeople = peopleRepository.findById(peopleId)
                    .orElseThrow(() -> new PeopleNotFoundException(
                            String.format(MSG_PEOPLE_NOT_FOUND, peopleId)));

            existingPeople.setName(peopleRequestDTO.getName());
            existingPeople.setDateOfBirth(peopleRequestDTO.getDateOfBirth());

            Long addressId = peopleRequestDTO.getAddressId();

            Address address = addressService.searchOrFail(addressId);

            List<Address> addressList = new ArrayList<>();
            addressList.add(address);
            existingPeople.setAddress(addressList);

            return peopleRepository.save(existingPeople);
        }

        public People searchOrFail(Long peopleId) {
            return peopleRepository.findById(peopleId)
                    .orElseThrow(() -> new PeopleNotFoundException(
                            String.format(MSG_PEOPLE_NOT_FOUND, peopleId)));
        }
    }

