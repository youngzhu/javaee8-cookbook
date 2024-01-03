package com.youngzy.book.javaee8cookbook.ch01.security;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

/**
 * @author youngzy
 * @since 2024-01-03
 */
@Stateful
public class UserBean {
    @PersistenceContext(unitName = "ch01-security-pu", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @RolesAllowed({Role.ADMIN, Role.OPERATOR})
    public void add(User user){
        em.persist(user);
    }

    @RolesAllowed({Role.ADMIN})
    public void remove(User user){
        em.remove(user);
    }

    @RolesAllowed({Role.ADMIN})
    public void update(User user){
        em.merge(user);
    }

    @PermitAll
    public List<User> get(){
        Query q = em.createQuery("SELECT u FROM User as u ");
        return q.getResultList();
    }
}
