package com.ecommerce.productcatalogueservices.tableInheritanceExample.joined;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="j_mentor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    private Long num_of_session;
}
