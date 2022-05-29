package com.tdtu.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "shoe")
public class Shoe extends BaseEntity{
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

    @Column(name = "upper")
    private String upper;

    @Column(name = "midsole")
    private String midsole;

    @Column(name = "outsole")
    private String outsole;
}
