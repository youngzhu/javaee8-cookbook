package com.youngzy.book.javaee8cookbook.ch02.jta;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * @author youngzy
 * @since 2024-01-03
 */
@Stateful
public class UserBean {
    @PersistenceContext(unitName = "ch02-jta-pu", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public void add(User user){
        em.persist(user);
    }

    public void update(User user){
        em.merge(user);
    }

    public void remove(User user){
        em.remove(user);
    }

    public User findById(Long id){
        return em.find(User.class, id);
    }
}
