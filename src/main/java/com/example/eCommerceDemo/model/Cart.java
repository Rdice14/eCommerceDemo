package com.example.eCommerceDemo.model;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    private List<Integer> productList;
    private double totalPrice;
}
