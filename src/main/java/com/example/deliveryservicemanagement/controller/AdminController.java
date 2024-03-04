package com.example.deliveryservicemanagement.controller;

import com.example.deliveryservicemanagement.ds.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/home")
    public String homepage(Model model){
        model.addAttribute("staff",new Staff());
        return "dashboard";
    }
}
