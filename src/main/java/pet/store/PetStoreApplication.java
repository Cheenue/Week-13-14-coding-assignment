package pet.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan (basePackages = "com.entity")
public class PetStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetStoreApplication.class, args);
    }
}
