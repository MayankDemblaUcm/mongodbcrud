package com.ucmo.advancedb.projectwork.repository;

import com.ucmo.advancedb.projectwork.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


}
