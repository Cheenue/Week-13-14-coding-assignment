package com.petstore.controller;

import com.petstore.controller.model.PetStoreData;
import com.petstore.controller.model.PetStoreEmployee;
import com.petstore.dao.PetStoreDao;
import com.petstore.entity.PetStore;
import com.petstore.service.PetStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pet_store")
@Slf4j //can use log as an instance variable
public class PetStoreController {
    private final PetStoreDao petStoreDao;

    @Autowired
    PetStoreService petStoreService;

    @Autowired
    public PetStoreController(PetStoreService petStoreService,
                              PetStoreDao petStoreDao) {
        this.petStoreService = petStoreService;
        this.petStoreDao = petStoreDao;
    }

    @PostMapping("/pet_store")
    @ResponseStatus(HttpStatus.CREATED)
    public PetStore createPetStore(@RequestBody PetStore petStoreData) {
        log.info("Received a POST request to create a pet store: {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }

    @GetMapping(value = "/id/{id}")
    public Optional<PetStore> getPetstore(@PathVariable("id") Long id) {
        log.info("Received a GET request to create a pet store: {}", petStoreService.findPetStoreById(id));
        return petStoreService.findPetStoreById(id);
    }

    @PutMapping(value = "/update/{id}")
    public Optional<PetStore> petStore(@PathVariable("id") Long id, @RequestBody PetStore newPetStoreDetails) {
        Optional<PetStore> petStore = petStoreService.findPetStoreById(id);
        log.info("Received a PUT request to create a pet store: {}", petStoreService.findPetStoreById(id));

        PetStore existingPetStore = petStore.get();

        existingPetStore.setPetStoreName(newPetStoreDetails.getPetStoreName());
        existingPetStore.setPetStoreAddress(newPetStoreDetails.getPetStoreAddress());
        existingPetStore.setPetStoreCity(newPetStoreDetails.getPetStoreCity());
        existingPetStore.setPetStoreState(newPetStoreDetails.getPetStoreState());
        existingPetStore.setPetStoreZip(newPetStoreDetails.getPetStoreZip());
        existingPetStore.setPetStorePhone(newPetStoreDetails.getPetStorePhone());


        petStoreService.savePetStore(existingPetStore);

        return petStoreService.findPetStoreById(id);
    }

    @PostMapping ("/pet_store/{petStoreId}/employee")
    @ResponseStatus (HttpStatus.CREATED)
    public PetStoreEmployee addEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee employee) {
        log.info("Received a POST request to add an employee to the pet store with ID: {}", petStoreId);
        return petStoreService.saveEmployee(petStoreId, employee);
    }

    @GetMapping("/pet_store")
    public List<PetStoreData> getAllPetStores() {
        return petStoreService.retrieveAllPetStores();
    }
    @GetMapping("/pet_store/{petStoreId}")
    public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
        return petStoreService.retrievePetStoreById(petStoreId);
    }

    @DeleteMapping("/pet_store/{petStoreId}")
    public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
        log.info("Deleting pet store with ID: {}", petStoreId);
        petStoreService.deletePetStoreById(petStoreId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Pet store deleted successfully");
        return response;
    }
}