package com.example.eCommerceDemo.Dao;

import com.example.eCommerceDemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDaoImpl implements ProductDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int createProduct(Product product) {

        String sql = "INSERT INTO "
                + getTable() +
                " (name, price, category)" +
                " VALUES (?, ?, ?);";

        return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getCategory());
    }

    @Override
    public Product fetchProductById(int id) {

        String sql = "SELECT * FROM " +
                getTable() +
                " WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), id);
    }

    @Override
    public int updateProduct(Product product, int id) {

        String sql = "UPDATE " +
                getTable() +
                " SET NAME = ?, " +
                " PRICE = ?, " +
                " CATEGORY = ?" +
                " WHERE id = ?";

        return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getCategory(), id);
    }

    @Override
    public int deleteProductById(int id) {

        String sql = "DELETE FROM " +
                getTable() +
                " WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM " + getTable();

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    private String getTable() {
        return "PRODUCTS";
    }
}
