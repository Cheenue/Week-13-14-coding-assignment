package com.petstore.service;

import com.petstore.controller.model.PetStoreCustomer;
import com.petstore.controller.model.PetStoreData;
import com.petstore.dao.PetStoreDao;
import com.petstore.entity.PetStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PetStoreService {
    @Autowired
    PetStoreDao petStoreDao;

    public PetStore savePetStore(PetStore petStore) {

        findOrCreatePetStore(petStore.getPetStoreId());
        petStoreDao.save(petStore);
        return null;
    }

    private PetStore findOrCreatePetStore(Long petStoreId) {
        if(petStoreId == null) {
            return new PetStore();
        } else {
            findPetStoreById(petStoreId);
            return null;
        }
    }

    public Optional<PetStore> findPetStoreById(Long petStoreId) {
        Optional<PetStore> petStore = petStoreDao.findById(petStoreId);//just calling the method

        return petStore;
    }

    private void copyPetStoreFields(PetStoreData petStoreData, PetStore petStore){
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    }
}
