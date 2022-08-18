package com.example.circlea;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private static String email2;


    public static ArrayList<User> users = new ArrayList<User>();

    public User(String firstName, String lastName, String email, String password,String phoneNumber,String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber=phoneNumber;
        this.address=address;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }
    public static  String getEmail2() {
        return email2;
    }

    public static void setEmail(String email) {
        email2 = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
