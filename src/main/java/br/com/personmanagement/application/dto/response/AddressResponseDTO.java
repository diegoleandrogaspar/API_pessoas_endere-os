package br.com.personmanagement.application.dto.response;

import br.com.personmanagement.domain.entity.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDTO {

    private Long id;
    private String street;
    private String cep;
    private String number;
    private String city;
    private String state;
    private AddressType type;

}
