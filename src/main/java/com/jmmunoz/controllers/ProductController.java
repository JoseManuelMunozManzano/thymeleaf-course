package com.jmmunoz.controllers;

import com.jmmunoz.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/product")
    public String getProduct() {
        return "redirect:/index";
    }

    @RequestMapping("/product/{id}")
    public String getProductById(@RequestParam Integer id, Model model) {
        model.addAttribute("product", productService.getProduct(id));

        return "product";
    }
}
