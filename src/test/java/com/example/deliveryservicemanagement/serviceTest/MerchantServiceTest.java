package com.example.deliveryservicemanagement.serviceTest;

import com.example.deliveryservicemanagement.dao.MerchantDao;
import com.example.deliveryservicemanagement.ds.Merchant;
import com.example.deliveryservicemanagement.service.MerchantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MerchantServiceTest {
    @Mock
    private MerchantDao merchantDao;

    @InjectMocks
    private MerchantService merchantService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMerchant(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("John Doe");
        when(merchantDao.save(merchant)).thenReturn(merchant);
        merchantService.saveMerchant(merchant);
        verify(merchantDao,times(1)).save(merchant);
    }

    @Test
    public void testDeleteMerchantById(){
        int id = 1;
        merchantDao.deleteById(id);
        verify(merchantDao,times(1)).deleteById(id);
    }

    @Test
    public void testFindMerchantById(){
        int id = 1;
        Merchant merchant = new Merchant();
        merchant.setId(id);
        merchant.setMerchantName("John Doe");
        when(merchantDao.findById(id)).thenReturn(Optional.of(merchant));
        Merchant foundMerchant = merchantService.findMerchantById(id);
        assertNotNull(foundMerchant);
        assertEquals(id,foundMerchant.getId());
        assertEquals("John Doe",foundMerchant.getMerchantName());
    }

    @Test
    public void testFindMerchantByIdNotFound(){
        int id =1;
        when(merchantDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,()-> merchantService.findMerchantById(id));
    }
    @Test
    public void testFindAllMerchant(){
        List<Merchant> merchantList = new ArrayList<>();
        merchantList.add(new Merchant());
        merchantList.add(new Merchant());
        when(merchantDao.findAll()).thenReturn(merchantList);
        List<Merchant> foundMerchantList = merchantService.findAllMerchant();
        assertEquals(2,foundMerchantList.size());
    }

    @Test
    public void testUpdateStaffData(){
        int id = 1;
        Merchant merchant = new Merchant();
        merchant.setId(id);
        merchant.setMerchantName("John Doe");
        when(merchantDao.findById(id)).thenReturn(Optional.of(merchant));
        when(merchantDao.save(merchant)).thenReturn(merchant);
        merchantService.updateMerchantData(id,merchant);
        verify(merchantDao,times(1)).save(merchant);
    }

    @Test
    public void  testUpdateStaffDataNotFound(){
        int id = 1;
        Merchant merchant = new Merchant();
        merchant.setId(id);
        merchant.setMerchantName("John Doe");
        when(merchantDao.findById(id)).thenReturn(Optional.empty());
        assertDoesNotThrow(()-> merchantService.updateMerchantData(id,merchant));
    }

    @Test
    public void testFindMerchantByName(){
        String name = "John Doe";
        Merchant merchant = new Merchant();
        merchant.setMerchantName(name);
        when(merchantDao.findMerchantByMerchantName(name)).thenReturn(merchant);
        Merchant foundMerchant = merchantService.findMerchantByName(name);
        assertNotNull(foundMerchant);
        assertEquals(name,foundMerchant.getMerchantName());
    }
}
