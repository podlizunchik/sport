package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String type;
    private String name;
    private String price;
    private String numberOfPackages;
    private String weightOfPacking;
    private String manufacturer;
    private String accountingDiscounts;
}
