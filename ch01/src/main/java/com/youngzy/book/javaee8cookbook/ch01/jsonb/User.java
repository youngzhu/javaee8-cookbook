package com.youngzy.book.javaee8cookbook.ch01.jsonb;

/**
 * @author youngzy
 * @since 2024-01-03
 */
public class User {
    private String name;
    private String email;

    public User(){

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + '}';
    }

    // getter/setter 不能省略
    // jsonb 基于它们取值和赋值
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
