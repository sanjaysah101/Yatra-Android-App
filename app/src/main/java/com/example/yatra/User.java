package com.example.yatra;

public class User {
    public String fname, lname, email;
    public String fullName;
    public User(){
        
    }
    public User(String fullname,String email){
        this.fullName = fullname;
        this.email = email;
    }
    public User(String fname, String lname, String email){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
}
