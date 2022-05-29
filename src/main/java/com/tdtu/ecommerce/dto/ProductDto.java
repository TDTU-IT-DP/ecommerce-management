package com.tdtu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String name;
    private String colorCode;  // In common table with type CL
    private String brandCode;  // In common table with type B
    private String madeIn;
    private String quantity;
    private String codeItem;
    private boolean active;
}
