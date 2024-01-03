package com.youngzy.book.javaee8cookbook.ch03.jsonb;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.util.Date;
import java.util.Objects;

/**
 * @author youngzy
 * @since 2024-01-03
 */
public class User {
    private Long id;

    @JsonbProperty("fullName")
    private String name;

    private String email;

    @JsonbTransient
    private Double privateNumber;

    @JsonbDateFormat(JsonbDateFormat.DEFAULT_LOCALE)
    private Date dateCreated;

    public User(Long id, String name, String email, Double privateNumber, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.privateNumber = privateNumber;
        this.dateCreated = dateCreated;
    }

    private User(){
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name=" + name
                + ", email=" + email
                + ", privateNumber=" + privateNumber
                + ", dateCreated=" + dateCreated
                + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getPrivateNumber() {
        return privateNumber;
    }

    public void setPrivateNumber(Double privateNumber) {
        this.privateNumber = privateNumber;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
