package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;
import web.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    private final UserService userService;
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getListUsers());
        return "index";
    }
}