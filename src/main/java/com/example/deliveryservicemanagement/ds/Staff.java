package com.example.deliveryservicemanagement.ds;

import com.example.deliveryservicemanagement.ds.CustomerOrder;
import com.example.deliveryservicemanagement.ds.StaffLeave;
import com.example.deliveryservicemanagement.ds.StaffSalary;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Size(max = 100)
    private String staffName;

    @NotBlank
    @Size(max = 100)
    private String township;

    @NotBlank
    @Size(max = 255)
    private String address;

    @NotBlank
    @Pattern(regexp = "09\\d{9}")
    private String phoneNumber;

    @NotBlank
    @Size(max = 100)
    private String role;

    @NotNull
    private int successfulDeliveries = 0;

    @Column(name = "last_successful_delivery_date")
    private LocalDate lastSuccessfulDeliveryDate;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrderList = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<StaffLeave> staffLeaves = new ArrayList<>();

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    private List<StaffSalary> staffSalaries = new ArrayList<>();

    public void addLeave(StaffLeave staffLeave){
        staffLeave.setStaff(this);
        staffLeaves.add(staffLeave);
    }
}
