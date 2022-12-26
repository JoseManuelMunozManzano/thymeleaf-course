package com.jmmunoz.commands;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginCommand {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 4, max = 15)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
