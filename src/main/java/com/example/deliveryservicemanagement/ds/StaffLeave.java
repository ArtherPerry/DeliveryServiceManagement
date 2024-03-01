package com.example.deliveryservicemanagement.ds;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffLeave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;
    @NotNull(message = "Start Date is Required")
    private LocalDate startDate;
    @NotNull(message = "End Date is Required")
    private LocalDate endDate;
    @NotNull(message = "Leave Type is Required")
    private String leaveType;
    @NotNull(message = "Reason is Required")
    private String reason;
    @Pattern(regexp = "APPROVED|PENDING|REJECTED", message = "Status must be one of APPROVED, PENDING, or REJECTED")
    private String status;
    @ManyToOne
    @JoinColumn(name = "staff_id",nullable = false)
    private Staff staff;

}
