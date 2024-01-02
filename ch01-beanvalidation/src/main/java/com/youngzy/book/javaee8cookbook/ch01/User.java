package com.youngzy.book.javaee8cookbook.ch01;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.List;

/**
 * @author youngzy
 * @since 2024-01-02
 */
public class User {
    @NotBlank
    private String name;

    @Email
    private String email;

    @NotEmpty
    private List<@PositiveOrZero Integer> profileId;

    public User(String name, String email, List<Integer> profileId) {
        this.name = name;
        this.email = email;
        this.profileId = profileId;
    }

    public User(String name) {
        this.name = name;
        this.profileId = Arrays.asList(0);
    }
}
