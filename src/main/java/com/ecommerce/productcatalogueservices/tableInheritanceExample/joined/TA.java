package com.ecommerce.productcatalogueservices.tableInheritanceExample.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="j_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {
    private Long num_doubt_resolved;
}
