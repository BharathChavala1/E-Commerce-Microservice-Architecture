package com.example.demo.controller;

import com.example.demo.model.InventoryResponse;
import com.example.demo.service.InventoryService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/inventory/")
@Controller
public class InventoryController {

    @Autowired
    public InventoryService service;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<InventoryResponse> isInStock(@RequestParam List<String>  skuCode){

        return service.checkSkuCodeIsInStock(skuCode);
    }
}
