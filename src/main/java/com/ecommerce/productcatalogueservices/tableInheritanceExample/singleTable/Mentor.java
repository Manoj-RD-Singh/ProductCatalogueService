package com.ecommerce.productcatalogueservices.tableInheritanceExample.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private Long num_of_session;
}
