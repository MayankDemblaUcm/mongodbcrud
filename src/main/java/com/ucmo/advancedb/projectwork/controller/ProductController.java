package com.ucmo.advancedb.projectwork.controller;

import com.ucmo.advancedb.projectwork.model.Product;
import com.ucmo.advancedb.projectwork.model.Status;
import com.ucmo.advancedb.projectwork.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService ;

    // ## Get Mappings

    @GetMapping("/products")
    @Operation(summary = "Fetch all the available products")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/products/getbycode")
    @Operation(summary = "Get the product details by the product Code")
    public List<Product> getProductByCode(@RequestParam Long code){
        return productService.getProductByCode(code);
    }

    // 2. POST Mappings

    @PostMapping("/addproduct")
    @Operation(summary = "Add the product in the available products.")
    public Status addSingleRecord(@RequestBody  Product product){
        return productService.addSingleProduct(product) ;
    }

    @PostMapping("/addproducts")
    @Operation(summary = "Add the product in the available products.")
    public Status addRecords(@RequestBody List<Product> products){
         return productService.addRecord(products) ;
    }

    // PUT Mapping

    @PutMapping("/updateproduct")
    @Operation(summary = "Update the product in the available products.")
    public Status updateRecord(@RequestBody Product product){
        return productService.updateProduct(product) ;
    }


    @DeleteMapping("/deleteproduct")
    @Operation(summary = "Delete the product based on the code")
    public Status deleteRecord(@RequestParam int code){
        return productService.deleteProduct(code) ;
    }





}
