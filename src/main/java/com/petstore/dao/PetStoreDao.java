package com.petstore.dao;

import com.petstore.entity.PetStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetStoreDao extends JpaRepository <PetStore, Long> {
}
