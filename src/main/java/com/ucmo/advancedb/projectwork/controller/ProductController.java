package com.ucmo.advancedb.projectwork.controller;

import com.ucmo.advancedb.projectwork.model.Product;
import com.ucmo.advancedb.projectwork.model.Status;
import com.ucmo.advancedb.projectwork.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService ;


    @GetMapping("/products")
    @Operation(summary = "Fetch all the available products")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("/addproduct")
    @Operation(summary = "Add the product in the available products.")
    public Status updateRecords(List<Product> products){
         return productService.addRecord(products) ;
    }




}
