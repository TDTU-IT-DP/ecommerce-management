package com.tdtu.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "racket")
public class Racket extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "color_code")
    private String colorCode;  // In common table with type CL

    @Column(name = "brand_code")
    private String brandCode;  // In common table with type B

    @Column(name = "made_in")
    private String madeIn;

    @Column(name = "code_item")
    private String codeItem;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "active")
    private boolean active;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "length")
    private BigDecimal length;

    @Column(name = "stringing")
    private BigDecimal stringing;

}
