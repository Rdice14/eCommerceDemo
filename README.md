# e-CommerceDemo using HikariPool

### Setup
1. Run db.sql to initialize database
2. Make sure username and password used for connection is authorized by db
3. Run app

### Endpoints
> Get All Products: [GET] `http://localhost:8080/product`

> Create Product: [POST] `http://localhost:8080/product/create`
  ```
  {
    "name": "Pepper",
    "price": 9.99,
    "category": "Seasoning"
  }
  ```
> Edit Product: [PUT] `http://localhost:8080/product/edit`
  ```
  {
    "name": "Coffee",
    "price": 10.99,
    "category": "Beverage",
    "id": 2
  }
  ```
> Delete Product: [DELETE] `http://localhost:8080/product/delete/{id}`

> Get All Carts: [GET] `http://localhost:8080/cart`

> Create Cart [POST] `http://localhost:8080/cart/create`
  ```
  {
    "itemList": [1, 5, 2], //Id of products
  }
  ```
> Edit Cart [PUT] `http://localhost:8080/cart/create`
  ```
  {
    "itemList": [1, 5, 2],
    "id": 3 //Id of edited cart
  }
  ```
> Delete Cart [DELETE] `http://localhost:8080/cart/delete/{id}`

> Checkout [GET] `http://localhost:8080/checkout/{id}`
