package com.example.eCommerceDemo.Dao;

import com.example.eCommerceDemo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setCategory(rs.getString("category"));
        return product;
    }
}
