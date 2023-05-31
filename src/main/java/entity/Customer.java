package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Customer {
    @Id
    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    Set<PetStore> petStores = new HashSet<>();
}
