package com.fabio.springmvc.services.jpaservice;

import com.fabio.springmvc.domain.User;
import com.fabio.springmvc.services.UserService;
import com.fabio.springmvc.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    private EncryptionService encryptionService;

    @Autowired
    public UserServiceJpaDaoImpl(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<?> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public User getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(domainObject.getPassword()!=null){
            domainObject.setEncryptedPassword(
                    encryptionService.encryptString(domainObject.getPassword()));
        }
        User savedUser = em.merge(domainObject);
        em.getTransaction().commit();
        return savedUser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
    }
}
