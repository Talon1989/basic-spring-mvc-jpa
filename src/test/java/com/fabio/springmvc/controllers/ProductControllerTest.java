package com.fabio.springmvc.controllers;

import com.fabio.springmvc.domain.Product;
import com.fabio.springmvc.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.*;

import java.math.BigDecimal;
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

public class ProductControllerTest {

    @Mock // mocks the dependency injection
    ProductService productService;

    @InjectMocks // sets up controller and injects mock objects into it
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test // tests listProducts(Model model)
    public void testList() throws Exception {

        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productService.listAll()).thenReturn((List)products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/list"))
                .andExpect(model().attribute("products", hasSize(2)));

    }

    @Test
    public void testShow() throws Exception {
        when(productService.getById(1)).thenReturn(new Product());
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testNew() throws Exception {
        verifyZeroInteractions(productService);
        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testEdit() throws Exception {
        when(productService.getById(1)).thenReturn(new Product());
        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {

        Product returnProduct = new Product();
        returnProduct.setId(1); returnProduct.setDescription("Test Description");
        returnProduct.setPrice(new BigDecimal("12.00")); returnProduct.setImageUrl("exmaple.com");

        when(productService.saveOrUpdate(Matchers.<Product>any()))
                .thenReturn(returnProduct);

        mockMvc.perform(post("/product")
            .param("id","1").param("description", "Test Description")
                .param("price","12.00").param("imageUrl","example.com")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/1"))
                .andExpect(model().attribute("product",instanceOf(Product.class)))
                .andExpect(model().attribute("product", hasProperty("id", is(1))))
                .andExpect(model().attribute("product", hasProperty("description", is("Test Description"))));

        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(boundProduct.capture());

        assertEquals("Test Description", boundProduct.getValue().getDescription());

    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));
    }

}