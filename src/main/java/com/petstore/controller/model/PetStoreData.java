package com.petstore.controller.model;

import com.petstore.entity.Customer;
import com.petstore.entity.Employee;
import com.petstore.entity.PetStore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PetStoreData {
    private Long petStoreId;
    private PetStoreData petStoreData;
    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhone;
    private Set<PetStoreCustomer> customers = new HashSet<>();
    private Set<PetStoreEmployee> employees = new HashSet<>();

    public PetStoreData(PetStore petStore) {
        this.petStoreId = petStore.getPetStoreId();
        this.petStoreName = petStore.getPetStoreName();
        this.petStoreAddress = petStore.getPetStoreAddress();
        this.petStoreCity = petStore.getPetStoreCity();
        this.petStoreState = petStore.getPetStoreState();
        this.petStoreZip = petStore.getPetStoreZip();
        this.petStorePhone = petStore.getPetStorePhone();

        for (Customer customer : petStore.getCustomers()) {
            this.customers.add(new PetStoreCustomer(customer));
        }

        for (Employee employee : petStore.getEmployees()) {
            this.employees.add(new PetStoreEmployee(employee));
        }
    }

    @Data
    @NoArgsConstructor
    public static class PetStoreCustomer {
        private Long customerId;
        private String customerName;
        private String customerEmail;

        public PetStoreCustomer(Customer customer) {
            this.customerId = customer.getCustomerId();
            this.customerName = customer.getCustomerFirstName();
            this.customerEmail = customer.getCustomerEmail();
        }
    }

    @Data
    @NoArgsConstructor
    public static class PetStoreEmployee {
        private Long employeeId;
        private String employeeFirstName;
        private String employeeLastName;
        private String employeeJobTitle;

        public PetStoreEmployee(Employee employee) {
            this.employeeId = employee.getEmployeeId();
            this.employeeFirstName = employee.getEmployeeFirstName();
            this.employeeLastName = employee.getEmployeeLastName();
            this.employeeJobTitle = employee.getEmployeeJobTitle();
        }
    }
}

