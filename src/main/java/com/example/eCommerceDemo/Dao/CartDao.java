package com.example.eCommerceDemo.Dao;

import com.example.eCommerceDemo.model.Cart;

import java.util.List;

public interface CartDao {

    int createCart(Cart cart);
    Cart fetchCartById(int id);
    int editCart(Cart cart, int id);
    int deleteCartById(int id);
    List<Cart> getAllCart();

}
