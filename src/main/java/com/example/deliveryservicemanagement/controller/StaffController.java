package com.example.deliveryservicemanagement.controller;

import com.example.deliveryservicemanagement.ds.Staff;
import com.example.deliveryservicemanagement.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/save")
    public String saveStaff(@ModelAttribute("staff") Staff staff, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "dashboard";
        }
        staffService.saveStaff(staff);
        return "redirect:/staff/findAll";
    }

    @GetMapping("/findById")
    public String findStaffById(@RequestParam("id") int id, Model model) {
        model.addAttribute("staff", staffService.findStaffById(id));
        return "staffDetails";
    }

    @GetMapping("/findAll")
    public String findAllStaff(Model model) {
        model.addAttribute("staffs", staffService.findAllStaff());
        return "allStaff";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable int id) {
        staffService.deleteStaffById(id);
        return "redirect:/staff/findAll";
    }

    @PostMapping("/update/{id}")
    public String updateStaffData(@PathVariable int id, @ModelAttribute("staff") Staff staff, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "dashboard";
        }
        staffService.updateStaffData(id, staff);
        return "redirect:/staff/findAll";
    }

    @GetMapping("findByName")
    public String findStaffByName(@RequestParam("name")String name,Model model){
        model.addAttribute("staffName",staffService.findStaffByName(name));
        return "staffDetails";
    }
}
