package com.ecommerce.productcatalogueservices.tableInheritanceExample.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class TA extends User{
    private Long num_doubt_resolved;
}
