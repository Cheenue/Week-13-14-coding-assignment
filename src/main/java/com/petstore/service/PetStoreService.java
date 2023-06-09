package com.petstore.service;

import com.petstore.controller.model.PetStoreData;
import org.springframework.stereotype.Service;


@Service
public class PetStoreService {

    public PetStoreData savePetStore(PetStoreData petStoreData) {

        findOrCreatePetStoreId(petStoreData.getPetStoreId());
        return null;
    }

    private void findOrCreatePetStoreId(Long petStoreId) {
        return null;
    }
}
