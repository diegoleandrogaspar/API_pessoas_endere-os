package br.com.personmanagement.repository;

import br.com.personmanagement.domain.entity.Address;
import br.com.personmanagement.domain.entity.enums.AddressType;
import br.com.personmanagement.infrastructure.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    private Address address;

    @BeforeEach
    void setUp() {
        //Given
        this.address = new Address();
        this.address.setCep("12345-678");
        this.address.setCity("Sample City");
        this.address.setNumber("123");
        this.address.setState("Sample State");
        this.address.setStreet("Sample Street");
        this.address.setType(AddressType.PRINCIPAL);
    }

    @DisplayName("Test Given Address Object when save then Return Saved Address")
    @Test
    void testGivenAddressObject_whenSave_thenReturnSavedAddress() {
        //When
        Address savedAddress = addressRepository.save(this.address);

        //Then
        assertNotNull(savedAddress.getId(), "Saved Address should have an ID after saving");
        assertEquals("12345-678", savedAddress.getCep(), "Saved Cep-Address should have the correct Cep");
        assertEquals("Sample City", savedAddress.getCity(), "Saved city-Address should have the correct city");
        assertEquals("123", savedAddress.getNumber(), "Saved number-Address should have the correct number");
        assertEquals("Sample State", savedAddress.getState(), "Saved State-Address should have the correct State");
        assertEquals("Sample Street", savedAddress.getStreet(), "Saved Street-Address should have the correct Street");
        assertEquals(AddressType.PRINCIPAL, savedAddress.getType(), "Saved Address should have the correct type");
    }


    @DisplayName("Test Given Address List when findAll then Return Address List")
    @Test
    void testGivenAddressList_whenFindAll_theReturnAddressList() {
        //Given
        Address address1 = new Address();
        address1.setCep("2222-22");
        address1.setCity("Rio de Janeiro");
        address1.setNumber("123");
        address1.setState("RJ");
        address1.setStreet("Rua Marechal");
        address1.setType(AddressType.SECUNDARIO);

        addressRepository.save(this.address);
        addressRepository.save(address1);

        //When
        List<Address> addressList = addressRepository.findAll();

        //Then
        assertNotNull(addressList);
        assertEquals(2, addressList.size());
    }

    @DisplayName("Test Given Address Object when findById then Return Address")
    @Test
    void testGivenAddressObject_whenFindById_thenReturneAddressObject(){
    //Given
    addressRepository.save(this.address);

    //When
    Address savedAddress = addressRepository.findById(address.getId()).get();

    //Then
    assertNotNull(savedAddress);
    assertEquals(savedAddress.getId(), savedAddress.getId());
    }

    @DisplayName("Test Given Address Object when Update Address then Return Update Address")
    @Test
    void testGivenAddressObject_whenUpdateAddress_thenReturnUpdateAddress() {
    //Given
    addressRepository.save(this.address);

    //When
    Address savedAddress = addressRepository.findById(address.getId()).get();
    savedAddress.setCep("12121");
    savedAddress.setType(AddressType.PRINCIPAL);

    Address updatedAddress =  addressRepository.save(savedAddress);

    //Then
    assertNotNull(updatedAddress);
    assertEquals("12121", updatedAddress.getCep());
    assertEquals(AddressType.PRINCIPAL, updatedAddress.getType());

    }
}



