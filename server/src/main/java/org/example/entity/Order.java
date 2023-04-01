package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private String region;
    private String type;
    private String name;
    private String numberOfPackages;
    private String weightOfPacking;
    private String taste;
    private String delivery;
    private String orderCost;
    private String status;
}
