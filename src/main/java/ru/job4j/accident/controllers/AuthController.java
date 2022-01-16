package ru.job4j.accident.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.models.User;
import ru.job4j.accident.services.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final PasswordEncoder encoder;
    private final AuthService service;

    public AuthController(PasswordEncoder encoder, AuthService service) {
        this.encoder = encoder;
        this.service = service;
    }

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            Model model
    ) {
        String message = null;
        if (error != null) {
            model.addAttribute("error", true);
            message = "Неверный логин и/или пароль!";
        }
        model.addAttribute("message", message);
        return "user/login";
    }

    @GetMapping("/register")
    public String registerPage(HttpSession session, Model model) {
        String error = (String) session.getAttribute("error");
        if (error != null) {
            model.addAttribute("error", error);
            session.removeAttribute("error");
        }
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(
            HttpServletRequest req,
            HttpSession session,
            @ModelAttribute User user
    ) {
        if (user.getPassword() == null) {
            session.setAttribute("error", "Пустой пароль!");
            return "redirect:/register";
        }
        if (!user.getPassword().equals(req.getParameter("checkPassword"))) {
            session.setAttribute("error", "Пароль не совпадает с подтверждением!");
            return "redirect:/register";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        if (!service.saveUser(user)) {
            session.setAttribute(
                    "error",
                    "Провал сохранения пользователя: "
                            + "указанный email или телефон уже используется!"
            );
            return "redirect:/register";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutPage(
            Authentication auth,
            HttpServletRequest request, HttpServletResponse response
    ) {
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
