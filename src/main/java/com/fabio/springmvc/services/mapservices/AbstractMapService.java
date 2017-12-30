package com.fabio.springmvc.services.mapservices;

import com.fabio.springmvc.domain.DomainObject;

import java.util.*;

public abstract class AbstractMapService {

    protected Map<Integer, DomainObject> domainMap;

    public AbstractMapService() {
        domainMap = new HashMap<>();
        loadDomainObjects();
    }

    protected abstract void loadDomainObjects();

    public List<DomainObject> listAll(){ return new ArrayList<>(domainMap.values());}

    public DomainObject getByid(Integer id){ return domainMap.get(id); }

    public DomainObject saveOrUpdate(DomainObject domainObject){
        if(domainObject!=null){
            if(domainObject.getId()==null){
                domainObject.setId(Collections.max(domainMap.keySet())+1);
            }
            domainMap.put(domainObject.getId(), domainObject);
            return domainObject;
        }
        else{ throw new RuntimeException("Object is null"); }
    }

    public void delete(Integer id) { domainMap.remove(id); }

}
