package com.jmmunoz.services;

import com.jmmunoz.domain.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(Integer id);

    List<Product> listProducts();
}
