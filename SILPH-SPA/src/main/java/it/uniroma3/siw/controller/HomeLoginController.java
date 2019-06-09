package it.uniroma3.siw.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeLoginController {

  @GetMapping("/")
  public String root() {
    return "images";
  }

  @GetMapping("/user")
  public String userIndex() {
    return "user/index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/access-denied")
  public String accessDenied() {
    return "/error/access-denied";
  }

}
