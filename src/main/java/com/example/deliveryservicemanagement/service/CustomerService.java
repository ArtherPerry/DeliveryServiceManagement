package com.example.deliveryservicemanagement.service;

import com.example.deliveryservicemanagement.dao.CustomerDao;
import com.example.deliveryservicemanagement.ds.Customer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;


    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }

    public void deleteCustomerById(long id) {
        customerDao.deleteById(id);
    }

    public Customer findCustomerById(long id) {
        return customerDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Customer> findAllCustomer() {
        return customerDao.findAll();
    }

    public void updateCustomerData(long id, Customer customer) {
        if (customerDao.findById(id).isPresent()) {
            customerDao.save(customer);
        }

    }

}
