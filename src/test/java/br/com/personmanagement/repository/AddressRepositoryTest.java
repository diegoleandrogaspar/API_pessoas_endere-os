package br.com.personmanagement.repository;

import br.com.personmanagement.domain.entity.Address;
import br.com.personmanagement.domain.entity.enums.AddressType;
import br.com.personmanagement.infrastructure.repository.AddressRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @DisplayName("Given Address Object when save then Return Saved Address")
    @Test
    void testGivenAddressObject_whenSave_thenReturnSavedAddress() {}


}
