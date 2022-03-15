package com.example.eCommerceDemo.Dao;

import com.example.eCommerceDemo.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartDaoImpl implements CartDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createCart(Cart cart) {

        List<String> productListStr = new ArrayList<>();

        for(int i = 0; i < cart.getProductList().size(); i++) {
            productListStr.add(cart.getProductList().get(i).toString());
        }

        String sql = "INSERT INTO "
                + getTable() + " (product_list, total_price)" +
                " VALUES (?, ?)";

        return jdbcTemplate.update(sql, productListStr.toString(), cart.getTotalPrice());
    }

    @Override
    public Cart fetchCartById(int id) {

        String sql = "SELECT * FROM " +
                getTable() +
                " WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new CartRowMapper(), id);
    }

    @Override
    public int editCart(Cart cart, int id) {

        String sql = "UPDATE " +
                getTable() +
                " SET PRODUCT_LIST = ?, " +
                " TOTAL_PRICE = ? " +
                " WHERE id = ?";

        return jdbcTemplate.update(sql, cart.getProductList().toString(), cart.getTotalPrice(), id);

    }

    @Override
    public int deleteCartById(int id) {

        String sql = "DELETE FROM " +
                getTable() +
                " WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Cart> getAllCart() {
        String sql = "SELECT * FROM " + getTable();

        return jdbcTemplate.query(sql, new CartRowMapper());
    }

    private String getTable() {
        return "CART";
    }
}
