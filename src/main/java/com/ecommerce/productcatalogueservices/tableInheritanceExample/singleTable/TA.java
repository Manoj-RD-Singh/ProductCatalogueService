package com.ecommerce.productcatalogueservices.tableInheritanceExample.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_ta")
@DiscriminatorValue(value = "1")
public class TA extends User {
    private Long num_doubt_resolved;
}
