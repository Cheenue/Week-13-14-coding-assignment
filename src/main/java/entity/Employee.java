package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {
    @Id
    private Long employeeId;
    private Long petStoreId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeePhone;
    private String employeeJobTitle;

    @EqualsAndHashCode.Exclude //this will take care of any recursions that come up
    @ToString.Exclude
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_store_id")
    PetStore petStore = new PetStore();
}
