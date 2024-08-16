package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@ToString
public class Customer {
    public Customer(String id, String address, String name,String phoneNumber , LocalDate dob, String title) {
        this.id = id;
        this.address = address;
        this.name = title + "." + name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }
    private String id;
    private String name;
    private String address;
    private LocalDate dob;
    private String title;
    private String phoneNumber;
}
