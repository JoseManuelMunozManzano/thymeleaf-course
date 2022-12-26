package com.jmmunoz.controllers;

import com.jmmunoz.commands.LoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginCommand", new LoginCommand());

        return "loginform";
    }

    @RequestMapping("/logout-success")
    public String yourLoggedOut() {
        return "logout-success";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(@Valid LoginCommand loginCommand, BindingResult result) {
        if (result.hasErrors()) {
            return "loginForm";
        }

        return "redirect:index";
    }
}
