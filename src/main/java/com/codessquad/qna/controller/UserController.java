package com.codessquad.qna.controller;

import com.codessquad.qna.domain.User;
import com.codessquad.qna.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private List<User> users = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String createUserAccount(User user) {
        if (user == null) {
            return "redirect:/user/form";
        }
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "/user/update";
    }

   @PutMapping("/{id}/update")
    public String updateUserInfo(@PathVariable Long id, User updateUser) {
        User user = userRepository.findById(id).get();
        user.update(updateUser);
        userRepository.save(user);
        return "redirect:/users";
    }

}
