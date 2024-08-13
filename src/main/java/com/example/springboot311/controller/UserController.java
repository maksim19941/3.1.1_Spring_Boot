package com.example.springboot311.controller;


import com.example.springboot311.model.User;
import com.example.springboot311.servise.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserServiceImp userService;

    @Autowired
    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String getListUser(Model model) {
        model.addAttribute("peopleList", userService.getListUser());
        return "peopleList";
    }


    @GetMapping(value = "/addNewUser")
    public String newUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "addNewUser";
    }

    @PostMapping("/addNewUser")
    public String newUser(@ModelAttribute("user") User user) {
        userService.save(user.getName(), user.getSurname(), user.getAge());
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String editPageUser(@RequestParam("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam("id") long id) {
        userService.update(user, id);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/";
    }


}
