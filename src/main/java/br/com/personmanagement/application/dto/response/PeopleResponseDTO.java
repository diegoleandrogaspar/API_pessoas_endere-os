package br.com.personmanagement.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeopleResponseDTO{

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private List<AddressResponseDTO> address;
}
