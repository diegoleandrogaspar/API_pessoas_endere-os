package br.com.personmanagement.domain.service;

import br.com.personmanagement.domain.entity.Address;
import br.com.personmanagement.domain.entity.People;
import br.com.personmanagement.infrastructure.repository.AddressRepository;
import br.com.personmanagement.web.exception.AddressNotFoundException;
import br.com.personmanagement.web.exception.PeopleNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    public static final String MSG_ADDRESS_NOT_FOUND
            = "Address not found with ID: %d";

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Transactional
    public Address create(Address address) {
    return addressRepository.save(address);
    }

    public Address searchOrFail(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(
                        String.format(MSG_ADDRESS_NOT_FOUND, addressId)));
    }
}