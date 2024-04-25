package com.ecommerce.productcatalogueservices.tableInheritanceExample.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User{
    private Long num_of_session;
}
