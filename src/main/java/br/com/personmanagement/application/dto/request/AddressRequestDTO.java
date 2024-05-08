package br.com.personmanagement.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.personmanagement.domain.entity.enums.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDTO {

    @NotBlank
    private String street;

    @NotBlank
    private String cep;

    @NotBlank
    private String number;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotNull
    private AddressType type;

}
