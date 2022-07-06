package com.example.extraclasses;

public class Users {
    String email;
    String name;
    String age;
    String type;

    public Users(String email,String name, String age, String type) {

        this.email=email;
        this.name=name;
        this.age=age;
        this.type=type;



    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
