package com.fabio.springmvc.controllers;

import com.fabio.springmvc.domain.Customer;
import com.fabio.springmvc.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.Assert.*;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer()); customers.add(new Customer());
        when(customerService.listAll()).thenReturn((List)customers);
        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        when(customerService.getById(1)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNew() throws Exception {
        verifyZeroInteractions(customerService);
        mockMvc.perform(get("/customer/new"))
                .andExpect(status().is(200))
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception {
        when(customerService.getById(1)).thenReturn(new Customer());
        mockMvc.perform(get("/customer/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Customer returnCustomer = new Customer();
        returnCustomer.setId(1); returnCustomer.setFirstName("Fabio"); returnCustomer.setLastName("Olivetto");
        when(customerService.saveOrUpdate(Matchers.<Customer>any()))
                .thenReturn(returnCustomer);
        mockMvc.perform(post("/customer")
            .param("id","1").param("firstName","Fabio").param("lastName","Olivetto")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/1"))
                .andExpect(model().attribute("customer",instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("firstName", is("Fabio"))));

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/customer/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));
    }

}