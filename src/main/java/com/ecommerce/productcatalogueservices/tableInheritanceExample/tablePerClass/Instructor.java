package com.ecommerce.productcatalogueservices.tableInheritanceExample.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_instructor")
public class Instructor extends User{
    private String company;
}
