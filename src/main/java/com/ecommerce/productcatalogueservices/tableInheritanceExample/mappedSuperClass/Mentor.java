package com.ecommerce.productcatalogueservices.tableInheritanceExample.mappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="msc_mentor")
public class Mentor extends User {
    private Long num_of_session;
}
