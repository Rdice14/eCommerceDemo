package com.example.eCommerceDemo.controller;

import com.example.eCommerceDemo.Dao.CartDaoImpl;
import com.example.eCommerceDemo.Dao.ProductDaoImpl;
import com.example.eCommerceDemo.model.Cart;
import com.example.eCommerceDemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EcommerceController {

    @Autowired
    private ProductDaoImpl productDao;

    @Autowired
    private CartDaoImpl cartDao;

    @GetMapping("/product")
    public Map<String, Object> getAllProducts() {
        List<Product> products = productDao.getAllProduct();

        Map<String, Object> result = new HashMap<>();

        result.put("List of products", products);
        result.put("Code", 200);
        result.put("Message", "Successfully retrieved all product");

        return result;
    }

    @PostMapping("/product/create")
    public Map<String, Object> createProduct(@RequestBody Map<String, Object> body) {

        Product product = new Product();
        System.out.println(body);
        product.setName(body.get("name").toString());
        product.setPrice(Double.parseDouble(body.get("price").toString()));
        product.setCategory(body.get("category").toString());

        int code = productDao.createProduct(product);

        Map<String, Object> result = new HashMap<>();

        if (code == 1) {
            result.put("Code", 200);
            result.put("Message", "Successfully added product");
            result.put("Added product", product);
        } else {
            result.put("Code", 500);
            result.put("Message", "Internal server error");
        }

        return result;
    }

    @PutMapping("/product/edit")
    public Map<String, Object> editProduct(@RequestBody Map<String, Object> body) {

        Product product = new Product();
        product.setName(body.get("name").toString());
        product.setPrice(Double.parseDouble(body.get("price").toString()));
        product.setCategory(body.get("category").toString());

        int code = productDao.updateProduct(product, Integer.parseInt(body.get("id").toString()));

        Map<String, Object> result = new HashMap<>();

        if (code == 1) {
            result.put("Code", 200);
            result.put("Message", "Successfully edit product");
            result.put("Added product", product);
        } else {
            result.put("Code", 500);
            result.put("Message", "Internal server error");
        }

        return result;
    }

    @DeleteMapping("/product/delete/{id}")
    public Map<String, Object> deleteProduct(@PathVariable int id){
        int code = productDao.deleteProductById(id);

        Map<String, Object> result = new HashMap<>();

        if (code == 1) {
            result.put("Code", 200);
            result.put("Message", "Successfully delete product");
        } else {
            result.put("Code", 500);
            result.put("Message", "Internal server error");
        }

        return result;
    }

    @GetMapping("/cart")
    public Map<String, Object> getAllCarts() {
        List<Cart> carts = cartDao.getAllCart();

        Map<String, Object> result = new HashMap<>();

        result.put("List of products", carts);
        result.put("Code", 200);
        result.put("Message", "Successfully retrieved all carts");

        return result;
    }

    @PostMapping("/cart/create")
    public Map<String, Object> createCart(@RequestBody Map<String, Object> body) {
        Cart cart =  new Cart();

        List<Integer> productsInCart = new ArrayList<>();
        double totalPrice = 0;

        List<Object> productsRequested = (List<Object>) body.get("itemList");

        for(int i = 0; i < productsRequested.size(); i++) {
            Product product = productDao.fetchProductById(Integer.parseInt(productsRequested.get(i).toString()));
            productsInCart.add(Integer.parseInt(productsRequested.get(i).toString()));
            totalPrice += product.getPrice();
        }

        cart.setProductList(productsInCart);
        cart.setTotalPrice(totalPrice);

        int code = cartDao.createCart(cart);

        Map<String, Object> result = new HashMap<>();

        if (code == 1) {
            result.put("Code", 200);
            result.put("Message", "Successfully created cart");
        } else {
            result.put("Code", 500);
            result.put("Message", "Internal server error");
        }

        return result;
    }

    @PutMapping("/cart/edit")
    public Map<String, Object> editCart(@RequestBody Map<String, Object> body) {
        Cart cart = new Cart(); //Find cart

        //Edit cart
        List<Integer> productsInCart = new ArrayList<>();
        double totalPrice = 0;

        List<Object> productsRequested = (List<Object>) body.get("itemList");

        for(int i = 0; i < productsRequested.size(); i++) {
            Product product = productDao.fetchProductById(Integer.parseInt(productsRequested.get(i).toString()));
            productsInCart.add(Integer.parseInt(productsRequested.get(i).toString()));
            totalPrice += product.getPrice();
        }

        cart.setProductList(productsInCart);
        cart.setTotalPrice(totalPrice);

        int code = cartDao.editCart(cart, Integer.parseInt(body.get("id").toString()));

        Map<String, Object> result = new HashMap<>();

        if (code == 1) {
            result.put("Code", 200);
            result.put("Message", "Successfully edited cart");
        } else {
            result.put("Code", 500);
            result.put("Message", "Internal server error");
        }

        return result;
    }

    @DeleteMapping("/cart/delete/{id}")
    public Map<String, Object> deleteCart(@PathVariable int id) {

        int code = cartDao.deleteCartById(id);

        Map<String, Object> result = new HashMap<>();

        if (code == 1) {
            result.put("Code", 200);
            result.put("Message", "Successfully edited cart");
        } else {
            result.put("Code", 500);
            result.put("Message", "Internal server error");
        }

        return result;
    }

    @GetMapping("/checkout/{id}")
    public Map<String, Object> checkout(@PathVariable int id) {
        Cart cart = cartDao.fetchCartById(id);
        int code = cartDao.deleteCartById(id);
        List<Integer> products = cart.getProductList();

        HashMap<String, Double> items = new HashMap<>();

        for (Integer index : products) {
            Product product = productDao.fetchProductById(index);
            items.put(product.getName(), product.getPrice());
        }

        Map<String, Object> result = new HashMap<>();

        result.put("Message", "Successfully checkout");
        result.put("Items checked out", items);
        result.put("Total price", cart.getTotalPrice());

        return result;
    }
}
