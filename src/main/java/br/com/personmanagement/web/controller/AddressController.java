package br.com.personmanagement.web.controller;

import br.com.personmanagement.application.dto.request.AddressRequestDTO;
import br.com.personmanagement.application.dto.response.AddressResponseDTO;
import br.com.personmanagement.domain.entity.Address;
import br.com.personmanagement.domain.entity.People;
import br.com.personmanagement.domain.service.AddressService;
import br.com.personmanagement.infrastructure.assembler.AddressConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressConverter addressConverter;

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressResponseDTO> listAddress() {
        List<Address> addressList = addressService.getAll();
        return addressConverter.toCollectionDTO(addressList);
    }

    @GetMapping("/{addressId}")
    public AddressResponseDTO getAddressId(@PathVariable Long addressId) {
        Address address = addressService.searchOrFail(addressId);
        return addressConverter.toDto(address);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDTO createAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO) {
       Address address = addressConverter.toDomainObject(addressRequestDTO);
       address = addressService.create(address);
       return addressConverter.toDto(address);

    }

    @PutMapping("/{addressId}")
    public AddressResponseDTO update(@PathVariable Long addressId, @RequestBody @Valid AddressRequestDTO addressRequestDTO) {
        Address addressActual = addressService.searchOrFail(addressId);
        addressConverter.copyToDomainObject(addressRequestDTO, addressActual);
        addressActual = addressService.create(addressActual);

        return addressConverter.toDto(addressActual);
    }

}
