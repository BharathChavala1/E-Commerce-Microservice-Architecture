package com.example.demo.service;

import com.example.demo.model.Inventory;
import com.example.demo.model.InventoryResponse;
import com.example.demo.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    public InventoryRepository repository;

    @Transactional
    public List<InventoryResponse> checkSkuCodeIsInStock(List<String>  skuCodes) {
         List<Inventory> listOfInventory= repository.findBySkuCodeIn(skuCodes);
        return listOfInventory.stream().map(inventory -> InventoryResponse
                 .builder()
                 .skuCode(inventory.getSkuCode())
                 .isInStock(inventory.getQuantity()>0).build()).toList();
    }
}
