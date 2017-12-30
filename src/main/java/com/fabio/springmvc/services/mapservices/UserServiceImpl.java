package com.fabio.springmvc.services.mapservices;

import com.fabio.springmvc.domain.DomainObject;
import com.fabio.springmvc.domain.User;
import com.fabio.springmvc.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("map")
public class UserServiceImpl extends AbstractMapService implements UserService{

    @Override
    protected void loadDomainObjects() {

    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getByid(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
