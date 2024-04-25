package com.ecommerce.productcatalogueservices.tableInheritanceExample.singleTable;

import jakarta.persistence.*;

@Entity(name="st_user")
@Inheritance(strategy =  InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
