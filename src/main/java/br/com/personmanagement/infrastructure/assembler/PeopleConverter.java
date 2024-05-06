package br.com.personmanagement.infrastructure.assembler;

import br.com.personmanagement.application.dto.request.PeopleRequestDTO;
import br.com.personmanagement.application.dto.response.PeopleResponseDTO;
import br.com.personmanagement.domain.entity.Address;
import br.com.personmanagement.domain.entity.People;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeopleConverter implements Converter<People, PeopleResponseDTO, PeopleRequestDTO>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public People toDomainObject(PeopleRequestDTO peopleRequestDTO) {
            return modelMapper.map(peopleRequestDTO, People.class);
    }

    @Override
    public PeopleResponseDTO toDto(People people) {
        return modelMapper.map(people, PeopleResponseDTO.class);
    }

    @Override
    public List<PeopleResponseDTO> toCollectionDTO(List<People> peopleList) {
        return peopleList.stream()
                .map(people ->  toDto(people))
                .collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(PeopleRequestDTO peopleRequestDTO, People people) {
        people.setAddress((List<Address>) new Address());

        modelMapper.map(peopleRequestDTO, people);
    }
}
