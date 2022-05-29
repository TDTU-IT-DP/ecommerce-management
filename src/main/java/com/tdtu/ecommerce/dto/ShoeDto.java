package com.tdtu.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoeDto extends ProductDto{
    private String upper;
    private String midsole;
    private String outsole;
}
