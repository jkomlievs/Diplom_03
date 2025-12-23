package org.example;

public class UserLogin {

    public String email;
    public String password;

    private UserLogin() {
    }

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}