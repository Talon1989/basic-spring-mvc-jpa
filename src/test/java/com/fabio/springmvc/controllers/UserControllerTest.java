package com.fabio.springmvc.controllers;

import com.fabio.springmvc.domain.User;
import com.fabio.springmvc.services.UserService;
import org.hamcrest.Matcher;
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

public class UserControllerTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testList() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(userService.listAll()).thenReturn((List)users);
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attribute("users", hasSize(2)));
    }

    @Test
    public void testShow() throws Exception {
        int num = 1;
        when(userService.getById(num)).thenReturn(new User());
        mockMvc.perform(get("/user/"+num))
                .andExpect(status().isOk())
                .andExpect(view().name("user/show"))
                .andExpect(model().attribute("user", instanceOf(User.class)));
    }

    @Test
    public void testNew() throws Exception {
        verifyZeroInteractions(userService);
        mockMvc.perform(get("/user/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userform"))
                .andExpect(model().attribute("user", instanceOf(User.class)));
    }

    @Test
    public void testEdit() throws Exception {
        when(userService.getById(1)).thenReturn(new User());
        mockMvc.perform(get("/user/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/userform"))
                .andExpect(model().attribute("user", instanceOf(User.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        User user = new User();
        user.setId(1); user.setUsername("myname");
        when(userService.saveOrUpdate(Matchers.<User>any()))
                .thenReturn(user);
        mockMvc.perform(post("/user")
            .param("id","1").param("username","myname")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/1"))
                .andExpect(model().attribute("user", instanceOf(User.class)))
                .andExpect(model().attribute("user", hasProperty("id", is(1))))
                .andExpect(model().attribute("user", hasProperty("username", is("myname"))));
        ArgumentCaptor<User> boundUser = ArgumentCaptor.forClass(User.class);
        verify(userService).saveOrUpdate(boundUser.capture());
        assertEquals("myname", boundUser.getValue().getUsername());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }
}