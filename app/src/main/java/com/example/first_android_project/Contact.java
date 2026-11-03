package com.example.first_android_project;

public class Contact {

    String name;
    String number;
    int avatar;

    public Contact(String name, String number, int avatar) {
        this.name = name;
        this.number = number;
        this.avatar = avatar;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
