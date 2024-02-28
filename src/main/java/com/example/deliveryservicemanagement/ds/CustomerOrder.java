package com.example.deliveryservicemanagement.ds;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private int productAmount;

    @NotNull
    private int deliveryCharge;

    @NotNull
    private BigDecimal total;

    private String deliveryStatus;

    private String paymentStatus;

    private String remark;

    @ManyToOne
    private Merchant merchant;

    @ManyToOne
    private Staff staff;

    @ManyToOne
    private Customer customer;
}
