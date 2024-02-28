package com.example.deliveryservicemanagement.service;

import com.example.deliveryservicemanagement.dao.MerchantDao;
import com.example.deliveryservicemanagement.ds.Merchant;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    @Autowired
    private MerchantDao merchantDao;


    public void saveMerchant(Merchant merchant) {
        merchantDao.save(merchant);
    }

    public void deleteMerchantById(int id) {
        merchantDao.deleteById(id);
    }

    public Merchant findMerchantById(int id) {
        return merchantDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Merchant> findAllMerchant() {
        return merchantDao.findAll();
    }

    public void updateMerchantData(int id, Merchant merchant) {
        if (merchantDao.findById(id).isPresent()) {
            merchantDao.save(merchant);
        }

    }

    public Merchant findMerchantByName(String name){
        return merchantDao.findMerchantByMerchantName(name);
    }

}
