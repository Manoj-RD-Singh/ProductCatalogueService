package com.ecommerce.productcatalogueservices.tableInheritanceExample.joined;

import jakarta.persistence.*;

@Entity(name="j_user")
@Inheritance(strategy =  InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
