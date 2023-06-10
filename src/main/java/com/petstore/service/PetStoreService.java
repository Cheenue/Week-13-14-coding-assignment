package com.petstore.service;

import com.petstore.controller.model.PetStoreCustomer;
import com.petstore.controller.model.PetStoreData;
import com.petstore.dao.PetStoreDao;
import com.petstore.entity.PetStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PetStoreService {
    @Autowired
    PetStoreDao petStoreDao;

    public PetStoreData savePetStore(PetStoreData petStoreData) {

        findOrCreatePetStore(petStoreData.getPetStoreId());

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

    private PetStore findPetStoreById(Long petStoreId) {
        petStoreDao.findById(petStoreId);//just calling the method
        return null;
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
