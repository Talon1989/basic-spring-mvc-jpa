package com.fabio.springmvc.services.jpaservice;

import com.fabio.springmvc.config.JpaIntegrationConfig;
import com.fabio.springmvc.domain.Customer;
import com.fabio.springmvc.domain.User;
import com.fabio.springmvc.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles({"jpadao"})
public class CustomerServiceJpaDaoImplTest {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = (List<Customer>)customerService.listAll();
        assert customers.size()==2;
    }

    @Test
    public void testSaveWithUser() throws Exception {
        Customer customer = new Customer();
        User user = new User(); user.setPassword("password");
        customer.setUser(user);
        Customer savedCustomer = customerService.saveOrUpdate(customer);
        assert  savedCustomer.getUser().getId()!=null;
    }
}
