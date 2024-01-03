package com.youngzy.book.javaee8cookbook.ch01.jsonb;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;

/**
 * @author youngzy
 * @since 2024-01-03
 */
public class JsonbDemo {
    public static void main(String[] args) throws Exception {
        User user = new User("Elder", "elder@eldermoraes.com");

        Jsonb jb = JsonbBuilder.create();
        String jsonUser = jb.toJson(user);
        User u = jb.fromJson(jsonUser, User.class);

        jb.close();
        System.out.println("json: " + jsonUser);
        System.out.println("user: " + u);
    }
}
