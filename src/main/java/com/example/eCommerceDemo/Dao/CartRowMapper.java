package com.example.eCommerceDemo.Dao;

import com.example.eCommerceDemo.model.Cart;
import com.example.eCommerceDemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartRowMapper implements RowMapper<Cart> {
    @Autowired
    private ProductDaoImpl productDao;

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cart cart = new Cart();

        String itemListStr = rs.getString("product_list")
                .replaceAll("\\]", "")
                .replaceAll("\\[", "");

        List<String> itemList = new ArrayList<>(Arrays.asList(itemListStr.split(", ")));

        System.out.println(itemList);

        List<Integer> productList = new ArrayList<>();

        for (String s : itemList) {
            productList.add(Integer.parseInt(s));
        }

        cart.setProductList(productList);
        cart.setTotalPrice(rs.getDouble("total_price"));

        return cart;
    }
}
