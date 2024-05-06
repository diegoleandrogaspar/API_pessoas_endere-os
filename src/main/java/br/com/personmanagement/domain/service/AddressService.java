package br.com.personmanagement.service;

import br.com.personmanagement.api.entity.Address;
import br.com.personmanagement.api.entity.People;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    public void configureMainAddress(People people) {
        Address mainAddress = people.getMainAddress();
        if (mainAddress != null) {
            people.setMainAddress(mainAddress);
        }
    }

}
