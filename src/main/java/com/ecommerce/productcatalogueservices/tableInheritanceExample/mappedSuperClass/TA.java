package com.ecommerce.productcatalogueservices.tableInheritanceExample.mappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_ta")
public class TA extends User {
    private Long num_doubt_resolved;
}
