package com.ucmo.advancedb.projectwork.service;

import com.ucmo.advancedb.projectwork.model.Product;
import com.ucmo.advancedb.projectwork.model.Status;
import com.ucmo.advancedb.projectwork.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository ;


    public List<Product> getProducts(){
        Pageable pageable = PageRequest.of(0, 10); // Fetch the first 10 records
        return productRepository.findAll(pageable).getContent();
    }

    public Status addRecord(List<Product> products) {

        try {
            productRepository.saveAll(products);
            return Status.builder()
                    .status("Success")
                    .description("Successfully saved.")
                    .build();
        }catch (Exception exception){
            return Status.builder()
                    .status("Failure")
                    .description("Failed to Save in Database, Please Try in sometime.")
                    .build();
        }
    }

}
