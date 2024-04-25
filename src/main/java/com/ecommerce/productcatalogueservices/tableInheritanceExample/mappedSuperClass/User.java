package com.ecommerce.productcatalogueservices.tableInheritanceExample.mappedSuperClass;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
