package com.fabio.springmvc.controllers;

import com.fabio.springmvc.domain.User;
import com.fabio.springmvc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listUsers(Model model){
        model.addAttribute("users",userService.listAll());
        return "user/list";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id, Model model){
        model.addAttribute("user",userService.getById(id));
        return "user/show";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "user/userform";
    }

    @PostMapping
    public String saveOrUpdateUser(User user){
        User savedUser = userService.saveOrUpdate(user);
        return "redirect:/user/"+savedUser.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "user/userform";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/user/list";
    }

}
