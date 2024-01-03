package com.youngzy.book.javaee8cookbook.ch01.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.ejb.embeddable.EJBContainer;

import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class SecurityTest {

    @Test
    public void asAdmin() throws Exception {
        adminExecutor.run(() -> {
            userBean.add(new User(1L, "user1", "user1@user.com"));
            userBean.add(new User(2L, "user2", "user2@user.com"));
            userBean.add(new User(3L, "user3", "user3@user.com"));
            userBean.add(new User(4L, "user4", "user4@user.com"));

            List<User> list = userBean.get();

            list.forEach((user) -> {
                userBean.remove(user);
            });

            assertEquals("userBean.get()", 0, userBean.get().size());

        });
    }

    @Test
    public void asOperator() throws Exception {
        operatorExecutor.run(() -> {
            userBean.add(new User(1L, "user1", "user1@user.com"));
            userBean.add(new User(2L, "user2", "user2@user.com"));
            userBean.add(new User(3L, "user3", "user3@user.com"));
            userBean.add(new User(4L, "user4", "user4@user.com"));

            List<User> list = userBean.get();

            list.forEach((user) -> {
                try {
                    userBean.remove(user);
                    fail("Operator was able to remove user " + user.getName());
                } catch (EJBAccessException e) {

                }
            });


            assertEquals("userBean.get()", 4, userBean.get().size());

        });
    }

    @Test
    public void asAnonymous() {
        try {
            userBean.add(new User(1L, "elder", "elder@eldermoraes.com"));
            fail("Anonymous user should not add users");
        } catch (EJBAccessException e) {

        }

        try {
            userBean.remove(new User(1L, "elder", "elder@eldermoraes.com"));
            fail("Anonymous user should not remove users");
        } catch (EJBAccessException e) {

        }

        try {
            userBean.get();
        } catch (EJBAccessException e) {
            fail("Everyone can list users");
        }
    }

    private EJBContainer ejbContainer;

    @EJB(name = "AdminExecutor")
    private RoleExecutor.AdminExecutor adminExecutor;

    @EJB(name = "OperatorExecutor")
    private RoleExecutor.OperatorExecutor operatorExecutor;

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