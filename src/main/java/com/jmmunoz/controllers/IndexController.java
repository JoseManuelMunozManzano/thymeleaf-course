package com.jmmunoz.controllers;

import com.jmmunoz.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @RequestMapping({"/", "index"})
    public String getIndex(Model model) {
        model.addAttribute("products", productService.listProducts());

        return "index";
    }

    @RequestMapping("secured")
    public String secured() {
        return "secured";
    }
}
