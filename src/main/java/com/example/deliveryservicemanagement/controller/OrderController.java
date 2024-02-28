package com.example.deliveryservicemanagement.controller;

import com.example.deliveryservicemanagement.ds.Customer;
import com.example.deliveryservicemanagement.ds.CustomerOrder;
import com.example.deliveryservicemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("customerOrder") CustomerOrder customerOrder, @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "dashboard";
        }
        orderService.saveOrder(customerOrder,customer);
        return "redirect:/order/findAll";
    }

}
