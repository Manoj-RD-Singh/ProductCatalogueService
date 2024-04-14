package com.ecommerce.productcatalogueservices.models;

import com.ecommerce.productcatalogueservices.enums.BaseModelStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
abstract public class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;
    private BaseModelStatus status;


}
