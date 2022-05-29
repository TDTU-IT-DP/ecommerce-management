package com.tdtu.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RacketDto extends ProductDto{
    private BigDecimal weight;
    private BigDecimal length;
    private BigDecimal stringing;

}
