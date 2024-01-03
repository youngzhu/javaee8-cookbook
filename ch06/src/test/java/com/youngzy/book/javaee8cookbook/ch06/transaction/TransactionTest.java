package com.youngzy.book.javaee8cookbook.ch06.transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import static org.junit.Assert.*;

public class TransactionTest {

    @Test
    public void test(){
        userBean.add(1);
        userBean.add(2);
        userBean.add(3);
        userBean.remove(2);
        int size = userBean.getActions().size();
        userBean.logout();
        assertEquals(2, size);
    }

    private EJBContainer ejbContainer;

    @EJB
    private UserBean userBean;

    @Before
    public void setUp() throws Exception {
        ejbContainer = EJBContainer.createEJBContainer();
        ejbContainer.getContext().bind("inject", this);
    }

    @After
    public void tearDown() throws Exception {
        ejbContainer.close();
    }
}