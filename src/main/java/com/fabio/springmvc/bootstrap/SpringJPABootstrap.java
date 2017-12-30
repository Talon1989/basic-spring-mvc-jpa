package com.fabio.springmvc.bootstrap;

import com.fabio.springmvc.domain.Address;
import com.fabio.springmvc.domain.Customer;
import com.fabio.springmvc.domain.Product;
import com.fabio.springmvc.services.CustomerService;
import com.fabio.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public SpringJPABootstrap(ProductService productService, CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProduct();
        loadCustomer();
    }

    private void loadProduct(){
        productService.saveOrUpdate(new Product("Product 1", new BigDecimal("12.99")
                , "http://example.com/product1"));
        productService.saveOrUpdate(new Product("Product 2", new BigDecimal("14.99")
                , "http://example.com/product2"));
        productService.saveOrUpdate(new Product("Product 3", new BigDecimal("34.99")
                , "http://example.com/product3"));
        productService.saveOrUpdate(new Product("Product 4", new BigDecimal("44.99")
                , "http://example.com/product4"));
        productService.saveOrUpdate(new Product("Product 5", new BigDecimal("25.99")
                , "http://example.com/product5"));
    }

    private void loadCustomer(){
        Customer customer1 = new Customer();
        customer1.setFirstName("Micheal");
        customer1.setLastName("Weston");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressLine1("1 Main St");
        customer1.getBillingAddress().setCity("Miami");
        customer1.getBillingAddress().setState("Florida");
        customer1.getBillingAddress().setZipCode("33101");
        customer1.setEmail("micheal@burnnotice.com");
        customer1.setPhoneNumber("305.333.0101");
        customerService.saveOrUpdate(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Fiona");
        customer2.setLastName("Glenanne");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressLine1("1 Key Biscane Ave");
        customer2.getBillingAddress().setCity("Miami");
        customer2.getBillingAddress().setState("Florida");
        customer2.getBillingAddress().setZipCode("33101");
        customer2.setEmail("fiona@burnnotice.com");
        customer2.setPhoneNumber("305.323.0233");
        customerService.saveOrUpdate(customer2);

        Customer customer3 = new Customer();
        customer3.setFirstName("Sam");
        customer3.setLastName("Axe");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressLine1("1 Little Cuba Road");
        customer3.getBillingAddress().setCity("Miami");
        customer3.getBillingAddress().setState("Florida");
        customer3.getBillingAddress().setZipCode("33101");
        customer3.setEmail("sam@burnnotice.com");
        customer3.setPhoneNumber("305.426.9832");
        customerService.saveOrUpdate(customer3);
    }

}
