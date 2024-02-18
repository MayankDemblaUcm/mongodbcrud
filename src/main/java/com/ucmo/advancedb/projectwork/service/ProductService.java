package com.ucmo.advancedb.projectwork.service;

import com.ucmo.advancedb.projectwork.model.Product;
import com.ucmo.advancedb.projectwork.model.Status;
import com.ucmo.advancedb.projectwork.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository ;

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Product> getProducts(){
        Pageable pageable = PageRequest.of(0, 10); // Fetch the first 10 records
        return productRepository.findAll(pageable).getContent();
    }

    public List<Product> getProductByCode(Long code){

        Query query = new Query() ;
        query.addCriteria(Criteria.where("code").is(code));
        return mongoTemplate.find(query, Product.class);
    }

    public Status addRecord(List<Product> products) {

        try {
            productRepository.saveAll(products);
            return Status.builder()
                    .status("Success")
                    .description("Successfully saved.")
                    .trace("Success")
                    .build();
        }catch (Exception exception){
            return Status.builder()
                    .status("Failure")
                    .description("Failed to Save in Database, Please Try in sometime.")
                    .trace(exception.getMessage())
                    .build();
        }
    }

    public Status addSingleProduct(Product product) {

        try {
            if(!this.validaterecordExist(product.getCode())){
                productRepository.save(product);
                return Status.builder()
                        .status("Success")
                        .description("Successfully saved.")
                        .build();
            }else{
                return Status.builder()
                        .status("Failure")
                        .description("Product Code already existing.")
                        .trace("Validation Error")
                        .build();
            }
        }catch (Exception exception){
            return Status.builder()
                    .status("Failure")
                    .description("Failed to Save in Database, Please Try in sometime.")
                    .trace(exception.getMessage())
                    .build();
        }
    }

    public Status updateProduct(Product product) {

        try {
            if(this.validaterecordExist(product.getCode())){
                productRepository.save(product);
                return Status.builder()
                        .status("Updated")
                        .description("Updated saved.")
                        .build();
            }else{
                return Status.builder()
                        .status("Failure")
                        .description("Product code does not existing. Try POST to save")
                        .trace("Validation Error")
                        .build();
            }
        }catch (Exception exception){
            return Status.builder()
                    .status("Failure")
                    .description("Failed to Save in Database, Please Try in sometime.")
                    .trace(exception.getMessage())
                    .build();
        }
    }

    public Status deleteProduct(long code){
        try{

            if(this.validaterecordExist(code)) {
                productRepository.deleteByCode(code);

                return Status.builder()
                        .status("Success")
                        .description("Deleted Product with code " + code)
                        .trace("Product Deleted Successfully")
                        .build() ;

            }else{
                return Status.builder()
                        .status("Failed")
                        .description("Failed to Delete the Record")
                        .trace("Product Code Does Not exist")
                        .build() ;
            }
        }catch (Exception exception){
            return Status.builder()
                    .status("Failed")
                    .description("Failed to Delete the Record")
                    .trace(exception.getMessage())
                    .build() ;
        }

    }

    private boolean validaterecordExist(Long code){
        Query query = new Query() ;
        query.addCriteria(Criteria.where("code").is(code));
         return mongoTemplate.find(query, Product.class).size() > 0 ;
    }

}
