package com.petstore.service;

import com.petstore.controller.model.PetStoreData;
import com.petstore.controller.model.PetStoreEmployee;
import com.petstore.dao.EmployeeDao;
import com.petstore.dao.PetStoreDao;
import com.petstore.entity.Employee;
import com.petstore.entity.PetStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class PetStoreService {
    @Autowired
    PetStoreDao petStoreDao;
    @Autowired
    private EmployeeDao employeeDao;

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
    @Transactional (readOnly = false)
    public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
        Optional<PetStore> petStore = findPetStoreById(petStoreId);
        Employee employee = findOrCreateEmployee(petStoreEmployee.getEmployeeId(), petStoreId);
        copyEmployeeFields(employee, petStoreEmployee);
        employee.setPetStore(petStore.get());
        petStore.get().getEmployees().add(employee);
        employeeDao.save(employee);
        PetStoreEmployee convertPetStoreEmployee = new PetStoreEmployee();
        convertPetStoreEmployee.setEmployeeId(employee.getEmployeeId());
        convertPetStoreEmployee.setEmployeePhone(employee.getEmployeePhone());
        convertPetStoreEmployee.setEmployeeFirstName(employee.getEmployeeFirstName());
        convertPetStoreEmployee.setEmployeeLastName(employee.getEmployeeLastName());
        convertPetStoreEmployee.setEmployeeJobTitle(employee.getEmployeeJobTitle());

        return convertPetStoreEmployee;
    }



    public Employee findEmployeeById(Long petStoreId, Long employeeId) {
        Optional<Employee> optionalEmployee = employeeDao.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return employee;
        } else {
            throw new NoSuchElementException("Employee not found with ID: " + employeeId);
        }
    }

    private Employee findOrCreateEmployee(Long employeeId, Long petStoreId) {
        if (petStoreId == null) {
            return new Employee();
        } else {
            Employee employee = findEmployeeById(employeeId, petStoreId);
            return employee != null ? employee : new Employee();
        }
    }

    private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
        employee.setEmployeeId(petStoreEmployee.getEmployeeId());
        employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
        employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
        employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
    }

    @Transactional
    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        List<PetStoreData> petStoreDataList = new LinkedList<>();

        for (PetStore petStore : petStores) {
            PetStoreData petStoreData = new PetStoreData(petStore);
            petStoreData.setCustomers(null);
            petStoreData.setEmployees(null);
            petStoreDataList.add(petStoreData);
        }

        return petStoreDataList;
    }

    private List<PetStoreData> convertToPetStoreDataList(List<PetStore> petStores) {
        List<PetStoreData> petStoreDataList = new ArrayList<>();
        for (PetStore petStore : petStores) {
            PetStoreData petStoreData = new PetStoreData();
            petStoreDataList.add(petStoreData);
        }
        return petStoreDataList;
    }

    private void removeCustomerAndEmployeeData(List<PetStoreData> petStoreDataList) {
        for (PetStoreData petStoreData : petStoreDataList) {
            petStoreData.setCustomers(null);
            petStoreData.setEmployees(null);
        }
    }
    @Transactional
    public PetStoreData retrievePetStoreById(Long petStoreId) {
        PetStore petStore = petStoreDao.findById(petStoreId).orElseThrow(NoSuchElementException::new);
        PetStoreData petStoreData = convertToPetStoreData(petStore);
        return petStoreData;
    }

    private PetStoreData convertToPetStoreData(PetStore petStore) {
        PetStoreData petStoreData = new PetStoreData();
        // Set relevant data from petStore to petStoreData
        return petStoreData;
    }

    @Transactional
    public void deletePetStoreById(Long petStoreId) {
        PetStore petStore = findPetStoreById(petStoreId);
        petStoreDao.delete(petStore);
    }

}
