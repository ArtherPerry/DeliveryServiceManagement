package com.example.deliveryservicemanagement.controller;

import com.example.deliveryservicemanagement.ds.Customer;
import com.example.deliveryservicemanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    public final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer")Customer customer, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "dashboard";
        }
        customerService.saveCustomer(customer);
        return "redirect:/customer/findAll";
    }

    @GetMapping("/findById")
    public String findCustomerById(@RequestParam("id")long id, Model model){
        model.addAttribute("customer",customerService.findCustomerById(id));
        return "customerDetail";
    }

    @GetMapping("/findAll")
    public String findAllCustomer(Model model){
        model.addAttribute("customers",customerService.findAllCustomer());
        return "customerTable";
    }

    @GetMapping("delete/{id}")
    public String deleteCustomer(@PathVariable long id){
        customerService.deleteCustomerById(id);
        return "redirect:/customer/findAll";
    }

    @PostMapping("/update/{id}")
    public String updateCustomerData(@PathVariable long id,@ModelAttribute("customer")Customer customer,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "dashboard";
        }
        customerService.updateCustomerData(id, customer);
        return "redirect:/customer/findAll";
    }
}
