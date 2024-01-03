package com.youngzy.book.javaee8cookbook.ch02.jta;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import java.util.Properties;

import static org.junit.Assert.*;

public class JtaTest {

    @Test
    public void validTransaction() throws Exception{
        User user  = new User(null, "Elder Moraes", "elder@eldermoraes.com");

        userBean.add(user);
        user.setName("John Doe");
        userBean.update(user);

        User userDb = userBean.findById(1L);
        assertEquals(userDb.getName(), "John Doe");

    }

    private EJBContainer ejbContainer;

    @EJB
    private UserBean userBean;

    @Before
    public void setUp() throws Exception {
        Properties p = new Properties();
        p.put("userDb", "new://Resource?type=DataSource");
        p.put("userDb.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("userDb.JdbcUrl", "jdbc:hsqldb:mem:userdatabase");

        ejbContainer = EJBContainer.createEJBContainer(p);
        ejbContainer.getContext().bind("inject", this);
    }

    @After
    public void tearDown() throws Exception {
        ejbContainer.close();
    }
}