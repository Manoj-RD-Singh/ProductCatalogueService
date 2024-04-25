package com.ecommerce.productcatalogueservices.tableInheritanceExample.mappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_instructor")
public class Instructor extends User {
    private String company;
}
