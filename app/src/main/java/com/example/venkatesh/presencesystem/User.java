package com.example.venkatesh.presencesystem;

/**
 * Created by venkatesh on 2/19/2018.
 */

public class User {
    private String email,status;

    public User(){

    }

    public User(String email, String status) {
    this.email = email;
        this.status = status;
    }

    public String getEmail() {

        return email;
    }


    public String getStatus() {
        return status;
    }
}
