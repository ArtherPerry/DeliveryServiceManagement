package com.example.deliveryservicemanagement.dao;

import com.example.deliveryservicemanagement.ds.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantDao extends JpaRepository<Merchant,Integer> {
    Merchant findMerchantByMerchantName(String name);
}
