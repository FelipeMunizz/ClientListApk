package com.femuniz.clientlist.models;

import java.util.Date;

public class UserModel {
    public static class User{
        public int ID_USER;
        public String USER_NAME;
        public String USER_EMAIL;
        public String USER_PASSWORD;
        public Date DATE_CHANGE;
        public boolean ACTIVE;
    }

    public static class LoginUser{
        public String UserEmail;
        public String UserPassword;

        public LoginUser(String email, String password){
            this.UserEmail = email;
            this.UserPassword = password;
        };
    }
}
