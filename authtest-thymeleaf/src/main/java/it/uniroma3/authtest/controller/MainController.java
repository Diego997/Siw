package it.uniroma3.authtest.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Map;

/**
 * The MainController is a Spring Boot Controller to handle
 * the generic interactions with the home pages, and that do not refer to specific entities
 */
@Controller
public class MainController {

    public MainController() {
        super();
    }

    /**
     * This method is called when a GET request is sent by the user to URL "/" or "/index".
     * This method prepares and dispatches the home view.
     *
     * @return the name of the home view
     */
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        return "home";
    }

    /**
     * This method is called when a GET request is sent by the user to URL "/welcome".
     * This method prepares and dispatches the welcome view.
     *
     * @return the name of the welcome view
     */
    @RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
    public String welcome(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);

        return "welcome";
    }

    /**
     * This method is called when a GET request is sent by the user to URL "/admin".
     * This method prepares and dispatches the admin view.
     *
     * @return the name of the admin view
     */
    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String admin(Model model) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = details.getAuthorities().iterator().next().getAuthority();    // get first authority
        model.addAttribute("username", details.getUsername());
        model.addAttribute("role", role);

        return "admin";
    }
}