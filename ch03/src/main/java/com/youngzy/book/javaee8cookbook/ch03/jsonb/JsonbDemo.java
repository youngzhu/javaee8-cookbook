package com.youngzy.book.javaee8cookbook.ch03.jsonb;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Date;

/**
 * @author youngzy
 * @since 2024-01-03
 */
public class JsonbDemo {
    public static void main(String[] args) throws Exception {
        long now = System.currentTimeMillis();
        User user = new User(now,
                "User" + now,
                "user" + now + "@eldermoraes.com",
                Math.random(),
                new Date());

        Jsonb jb = JsonbBuilder.create();
        String json = jb.toJson(user);
        System.out.println("json:" + json);
        System.out.println("user:" + user);

        jb.close();
    }
}
