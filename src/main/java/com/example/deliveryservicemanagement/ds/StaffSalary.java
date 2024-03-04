package com.example.deliveryservicemanagement.ds;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 0, message = "Basic salary must be a positive value")
    private int basicSalary;

    @Min(value = 0, message = "Incentive must be a positive value")
    private int totalIncentive;

    @Min(value = 0, message = "Bonus must be a positive value")
    private int bonus;

    @Min(value = 0, message = "Total Orders Delivered must be a positive value")
    private int totalOrdersDelivered;

    @Min(value = 0, message = "Total salary must be a positive value")
    private int totalSalary;

    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @NotNull(message = "Inventive Rate is Required")
    private int incentiveRate;

    @Pattern(regexp = "PAID|PENDING", message = "Payment status must be one of PAID or PENDING")
    private String paymentStatus;

    @Min(value = 0, message = "Deductions must be a positive value")
    private int deductions;
    @ManyToOne
    @JoinColumn(name = "staff_id",nullable = false)
    private Staff staff;

}