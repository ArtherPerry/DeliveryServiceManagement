package com.example.deliveryservicemanagement.ds;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Product amount cannot be null")
    @PositiveOrZero(message = "Product amount must be a positive or zero integer")
    private int productAmount;

    @NotNull(message = "Delivery charge cannot be null")
    @PositiveOrZero(message = "Delivery charge must be a positive or zero integer")
    private int deliveryCharge;

    @NotNull(message = "Total cannot be null")
    @DecimalMin(value = "0.00", inclusive = false, message = "Total must be greater than 0")
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private String paymentStatus;

    @Size(max = 255, message = "Remark must be at most 255 characters long")
    private String remark;

    @ManyToOne
    private Merchant merchant;

    @ManyToOne
    private Staff staff;

    @ManyToOne
    private Customer customer;


}
