package com.ecommerce.productcatalogueservices.tableInheritanceExample.tablePerClass;

import jakarta.persistence.*;

@Entity(name="tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
}
