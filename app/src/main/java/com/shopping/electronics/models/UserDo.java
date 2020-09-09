package com.shopping.electronics.models;

import java.io.Serializable;

/**
 * This class is model file for user related details
 */
public class UserDo implements Serializable {

    public String userId = "";
    public String name = "";
    public String email = "";
    public String phone = "";
    public String country = "";
    public String city = "";
    public String state = "";
    public String zipCode = "";
    public String gender = "";
    public String password = "";

    public UserDo(){}

}
