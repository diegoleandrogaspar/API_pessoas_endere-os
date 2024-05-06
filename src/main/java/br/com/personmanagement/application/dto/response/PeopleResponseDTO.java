package br.com.personmanagement.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PeopleRequestDTO {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    //private List<AddressResponseDTO> address;


}
