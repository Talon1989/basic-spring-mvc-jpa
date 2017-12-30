package com.fabio.springmvc.services.jpaservice;

import com.fabio.springmvc.domain.Customer;
import com.fabio.springmvc.services.CustomerService;
import com.fabio.springmvc.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstactJpaDaoService implements CustomerService {

//    private EntityManagerFactory emf;
//
//    @PersistenceUnit
//    public void setEmf(EntityManagerFactory emf) {
//        this.emf = emf;
//    }

    // to encrypt the pw of user since customer has a user field
    private EncryptionService encryptionService;

    @Autowired
    public CustomerServiceJpaDaoImpl(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("from Customer", Customer.class)
                .getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        if(domainObject.getUser()!=null && domainObject.getUser().getPassword()!=null){
            domainObject.getUser().setEncryptedPassword(
                    encryptionService
                            .encryptString(domainObject.getUser().getPassword()));
        }
        Customer savedCustomer = em.merge(domainObject);
        em.getTransaction().commit();
        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
    }
}
