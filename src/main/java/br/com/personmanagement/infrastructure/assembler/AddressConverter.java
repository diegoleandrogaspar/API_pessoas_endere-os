package br.com.personmanagement.infrastructure.assembler;

import br.com.personmanagement.application.dto.request.AddressRequestDTO;
import br.com.personmanagement.application.dto.response.AddressResponseDTO;
import br.com.personmanagement.domain.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressConverter implements Converter<Address, AddressResponseDTO, AddressRequestDTO>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Address toDomainObject(AddressRequestDTO addressRequestDTO) {
        return modelMapper.map(addressRequestDTO, Address.class);
    }

    @Override
    public AddressResponseDTO toDto(Address address) {
        return modelMapper.map(address, AddressResponseDTO.class);
    }

    @Override
    public List<AddressResponseDTO> toCollectionDTO(List<Address> addressList) {
        return addressList.stream()
                .map(address -> toDto(address))
                .collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(AddressRequestDTO addressRequestDTO, Address address) {
        modelMapper.map(addressRequestDTO, address);
    }
}
