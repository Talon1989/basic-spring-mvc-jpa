package com.fabio.springmvc.services.jpaservice;

import com.fabio.springmvc.config.JpaIntegrationConfig;
import com.fabio.springmvc.domain.*;
import com.fabio.springmvc.services.ProductService;
import com.fabio.springmvc.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles({"jpadao"})
public class UserServiceJpaDaoImplTest {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testSaveOfUser() throws Exception {

        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

    }

    @Test
    public void testSaveUserWithCustomer() throws Exception {
        User user = new User();
        user.setUsername("testU"); user.setPassword("testP");
        Customer customer = new Customer();
        customer.setFirstName("first"); customer.setLastName("last");
        user.setCustomer(customer);
        User savedUser = userService.saveOrUpdate(user);
        assert savedUser.getId()!=null;
        assert savedUser.getVersion()!=null;
        assert savedUser.getCustomer()!=null;
        assert savedUser.getCustomer().getId()!=null;
    }

    @Test
    public void testAddCartToUser() throws Exception{
        User user = new User();
        user.setUsername("testU"); user.setPassword("testP");
        user.setCart(new Cart());
        User savedUser = userService.saveOrUpdate(user);
        assert savedUser.getId()!=null;
        assert savedUser.getVersion()!=null;
        assert savedUser.getCart()!=null;
        assert savedUser.getCart().getId()!=null;
    }

    @Test
    public void testAddCartToUserWithCartDetails() throws Exception {

        User user = new User();
        user.setUsername("testU"); user.setPassword("testP");
        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>)productService.listAll();
        CartDetail cartItemOne = new CartDetail(); CartDetail cartItemTwo = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0)); cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemOne);
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId()!=null;
        assert savedUser.getVersion()!=null;
        assert savedUser.getCart()!=null;
        assert savedUser.getCart().getId()!=null;
        assert savedUser.getCart().getCartDetails().size()==2;

    }

    @Test
    public void testAddAndRemoveCartToUseWithCartDetails() throws Exception {

        User user = new User();
        user.setUsername("asd"); user.setPassword("pw");
        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>)productService.listAll();
        CartDetail cartItemOne = new CartDetail(); CartDetail cartItemTwo = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0)); cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemOne);
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);
        assert savedUser.getCart().getCartDetails().size() == 2;

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));
        assert savedUser.getCart().getCartDetails().size() == 1;

    }
}