package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImp;

@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/new")
    public String getNewUserForm(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(
                @RequestParam(name = "firstName") String firstName,
                @RequestParam(name = "lastName") String lastName,
                @RequestParam(name = "email") String email) {

        userService.add(new User(firstName, lastName, email));
        return "redirect:/";
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam(name = "id") long id) {
        userService.delete(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit")
    public String getEditUserForm(@RequestParam(name = "id") long id, ModelMap model) {
        model.addAttribute("user",  userService.get(id));
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editUser(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "id") Long id) {
        User user = userService.get(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userService.update(user);
        return "redirect:/";
    }
}
