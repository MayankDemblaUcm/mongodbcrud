package com.ucmo.advancedb.projectwork.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    private String _id;

    public Product(String code, String name, String main_category, String sub_category, String image, double ratings, String no_of_ratings, String actual_price, String discount_price) {
        this.code = code;
        this.name = name;
        this.main_category = main_category;
        this.sub_category = sub_category;
        this.image = image;
        this.ratings = ratings;
        this.no_of_ratings = no_of_ratings;
        this.actual_price = actual_price;
        this.discount_price = discount_price;
    }

    private String code;
    private String name;
    private String main_category;
    private String sub_category;
    private String image;
    private double ratings;
    private String no_of_ratings;
    private String actual_price;
    private String discount_price;

}
