package com.fabio.springmvc.services.mapservices;

import com.fabio.springmvc.domain.Customer;
import com.fabio.springmvc.services.CustomerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    public CustomerServiceImpl() {
        loadDomainObjects();
    }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer)super.getByid(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer)super.saveOrUpdate(domainObject);
    }

}
