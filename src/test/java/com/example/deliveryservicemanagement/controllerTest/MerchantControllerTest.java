package com.example.deliveryservicemanagement.controllerTest;

import com.example.deliveryservicemanagement.controller.MerchantController;
import com.example.deliveryservicemanagement.ds.Merchant;
import com.example.deliveryservicemanagement.service.MerchantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MerchantControllerTest {

    @Mock
    private MerchantService merchantService;

    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;

    private MerchantController merchantController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        merchantController = new MerchantController(merchantService);
    }
    @Test
    void saveMerchant_ValidMerchant_RedirectToFindAll(){
        Merchant merchant = new Merchant();
        String viewName = merchantController.saveMerchant(merchant,bindingResult);
        assertEquals("redirect:/merchant/findAll",viewName);
        verify(merchantService,times(1)).saveMerchant(merchant);
    }

    @Test
    void findMerchantById_ValidId_ModelContainsMerchant(){
        int id = 1;
        Merchant merchant = new Merchant();
        when(merchantService.findMerchantById(id)).thenReturn(merchant);
        String viewName = merchantController.findMerchantById(id,model);
        assertEquals("merchantDetail",viewName);
        verify(model,times(1)).addAttribute("merchant",merchant);
    }

    @Test
    void findAllMerchant_ModelContainsMerchantList(){
        List<Merchant> merchantList = new ArrayList<>();
        when(merchantService.findAllMerchant()).thenReturn(merchantList);
        String viewName = merchantController.findAllMerchant(model);
        assertEquals("allMerchant",viewName);
        verify(model,times(1)).addAttribute("merchants",merchantList);
    }

    @Test
    void deleteMerchant_ValidId_RedirectToFindAll() {
        int id = 1;
        String viewName = merchantController.deleteMerchant(id);
        assertEquals("redirect:/merchant/findAll", viewName);
        verify(merchantService, times(1)).deleteMerchantById(id);
    }

    @Test
    void updateMerchantData_ValidId_ValidMerchant_RedirectToFindAll() {
        int id = 1;
       Merchant merchant = new Merchant();
        String viewName = merchantController.updateMerchantData(id, merchant, bindingResult);
        assertEquals("redirect:/merchant/findAll", viewName);
        verify(merchantService, times(1)).updateMerchantData(id,merchant);
    }

    @Test
    void findMerchantByName_ValidName_ModelContainsMerchant() {
        String name = "John Doe";
       Merchant merchant = new Merchant();
        when(merchantService.findMerchantByName(name)).thenReturn(merchant);
        String viewName = merchantController.findMerchantByName(name, model);
        assertEquals("merchantDetail", viewName);
        verify(model, times(1)).addAttribute("merchantName", merchant);
    }
}
