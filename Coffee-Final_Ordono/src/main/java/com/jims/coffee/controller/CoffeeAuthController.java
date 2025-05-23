package com.jims.coffee.controller;

import com.jims.coffee.services.CoffeeUserService;
import com.jims.coffee.models.CoffeeUser;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoffeeAuthController {

    @Autowired
    CoffeeUserService coffeeUserService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("coffeeUser", new CoffeeUser());
        return "components/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("coffeeUser") @Valid CoffeeUser coffeeUser, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "components/login";
        }

        CoffeeUser foundUser = coffeeUserService.findByUsername(coffeeUser.getUsername());
        if (foundUser != null && coffeeUser.getPassword().equals(foundUser.getPassword())) {
            session.setAttribute("coffeeUser", foundUser);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid credentials");
        }

        return "components/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/change-password")
    public String showChangePassword(Model model) {
        model.addAttribute("coffeeUser", new CoffeeUser());
        return "components/change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute("coffeeUser") CoffeeUser coffeeUser,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "New passwords do not match.");
            return "components/change-password";
        }
        boolean changed = coffeeUserService.changePassword(coffeeUser.getUsername(), oldPassword, newPassword);
        if (changed) {
            model.addAttribute("success", "Password changed successfully. You can now log in with your new password.");
        } else {
            model.addAttribute("error", "Invalid username or old password.");
        }
        return "components/change-password";
    }
}
