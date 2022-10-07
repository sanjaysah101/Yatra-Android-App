package com.example.yatra;

public class User {
    public String fname, lname, email;
    public String fullName;

    public User(){}

    public User(String fullname,String email){
        this.fullName = fullname;
        this.email = email;
    }
    public User(String fname, String lname, String email){
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
