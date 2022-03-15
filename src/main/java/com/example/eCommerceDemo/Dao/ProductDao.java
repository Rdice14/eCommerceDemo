package com.example.eCommerceDemo.Dao;

import com.example.eCommerceDemo.model.Product;

import java.util.List;

public interface ProductDao {

    int createProduct(Product product);
    Product fetchProductById(int id);
    int updateProduct(Product product, int id);
    int deleteProductById(int id);
    List<Product> getAllProduct();

}
