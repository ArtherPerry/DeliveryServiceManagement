package com.example.deliveryservicemanagement.service;

import com.example.deliveryservicemanagement.dao.CustomerDao;
import com.example.deliveryservicemanagement.dao.CustomerOrderDao;
import com.example.deliveryservicemanagement.ds.Customer;
import com.example.deliveryservicemanagement.ds.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    public CustomerOrderDao customerOrderDao;
    @Autowired
    public CustomerDao customerDao;

    public void saveOrder(CustomerOrder customerOrder, Customer customer){
        customerOrderDao.save(customerOrder);
        customerDao.save(customer);
    }
}
