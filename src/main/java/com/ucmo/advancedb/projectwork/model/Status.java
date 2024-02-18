package com.ucmo.advancedb.projectwork.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Status {


    private String status ;

    private String description ;

    private String trace ;

}
