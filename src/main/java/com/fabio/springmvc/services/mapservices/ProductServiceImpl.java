package com.fabio.springmvc.services.mapservices;

import com.fabio.springmvc.domain.DomainObject;
import com.fabio.springmvc.domain.Product;
import com.fabio.springmvc.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

//    private Map<Integer, Product> products;

    public ProductServiceImpl() {
        loadDomainObjects();
    }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();
    }

    @Override
    public List<DomainObject> listAll() { return  super.listAll(); }

//    private void loadProducts() {
//        domainMap = new HashMap<>();
//        products.put(1
//                , new Product(1,"Product 1", new BigDecimal("12.99")
//                        ,"http://example.com/product1"));
//        products.put(2
//                , new Product(2,"Product 2", new BigDecimal("14.99")
//                        ,"http://example.com/product2"));
//        products.put(3
//                , new Product(3,"Product 3", new BigDecimal("34.99")
//                        ,"http://example.com/product3"));
//        products.put(4
//                , new Product(4,"Product 4", new BigDecimal("44.99")
//                        ,"http://example.com/product4"));
//        products.put(5
//                , new Product(5,"Product 5", new BigDecimal("25.99")
//                        ,"http://example.com/product5"));
//    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getByid(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

//    @Override
//    public void delete(Integer id) {
//        super.delete(id);
//    }
}
