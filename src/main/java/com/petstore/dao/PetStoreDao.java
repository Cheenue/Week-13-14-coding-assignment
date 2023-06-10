package com.petstore.dao;

import com.petstore.entity.PetStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetStoreDao extends JpaRepository <PetStore, Long> {
    Optional<PetStore> findByPetStoreId(Long petStoreId);
    List<PetStore> findAll();
}
