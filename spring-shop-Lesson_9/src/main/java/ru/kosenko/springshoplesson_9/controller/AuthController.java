package ru.kosenko.springshoplesson_9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kosenko.springshoplesson_9.service.RegisterService;
import ru.kosenko.springshoplesson_9.service.UserService;

@Controller
public class AuthController {

  private final UserService userService;
  private final RegisterService registerService;

  public AuthController(UserService userService, RegisterService registerService) {
    this.userService = userService;
    this.registerService = registerService;
  }

  @GetMapping("/login")
  public String loginForm() {
    return "login";
  }

  @GetMapping("/register")
  public String registerForm() {
    return "register";
  }

  @PostMapping("/register")
  public String register(@RequestParam String username, @RequestParam String password, @RequestParam String confirmpassword, Model model) {
    // TODO принимать два пароля и сравнивать
    if (password.equals(confirmpassword)) {
    // TODO валидация email regexp
    String token = registerService.sighUp(username, password); // TODO обработать ошибку и вывести пользователю
    model.addAttribute("token", token);
    return "register-confirm";}
    else return "/register"; // TODO обработать ошибку и вывести пользователю
  }

  @GetMapping("/register/confirm")
  public String registerConfirm(@RequestParam String token) {
    // TODO токен истек - что делать
    if (registerService.confirmRegistration(token)) {
      return "register-complete";
    }
    // TODO что-то выдавать разумное
    return "redirect:/";
  }
}
