package com.youngzy.book.javaee8cookbook.ch01;

import javax.json.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author youngzy
 * @since 2024-01-02
 */
public class JsonPointerDemo {

    public static void main(String[] args)  {
        JsonPointerDemo demo = new JsonPointerDemo();
        Object o = demo.getClass().getClassLoader().getResourceAsStream("user.json");

        try (InputStream is = JsonPointerDemo.class.getClassLoader().getResourceAsStream("user.json");
             JsonReader jr = Json.createReader(is)) {

            JsonStructure js = jr.read();
            JsonPointer jp = Json.createPointer("/user/profile");
            JsonValue jv = jp.getValue(js);
            System.out.println("profile: " + jv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
