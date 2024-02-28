package com.example.deliveryservicemanagement.ds;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(max = 100)
    private String customerName;

    @NotBlank
    @Pattern(regexp = "09\\d{9}")
    private String phoneNumber;

    @NotBlank
    @Size(max = 100)
    private String township;

    @NotBlank
    @Size(max = 255)
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrderList = new ArrayList<>();
}
