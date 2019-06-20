package it.uniroma3.authtest.controller;

import it.uniroma3.authtest.model.Richiesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.authtest.service.FotografiaService;
import it.uniroma3.authtest.service.FunzionarioService;
import it.uniroma3.authtest.service.RichiestaService;
/**
 * The MainController is a Spring Boot Controller to handle
 * the generic interactions with the home pages, and that do not refer to specific entities
 */
@Controller
public class MainController {

	@Autowired
	private FotografiaService fotografiaService;
	
	@Autowired
	private RichiestaService richiestaService;
	
	@Autowired
	private FunzionarioService funzionarioService;

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
		model.addAttribute("fotografie1", fotografiaService.tutti().subList(0, fotografiaService.tutti().size()/2));
		model.addAttribute("fotografie2", fotografiaService.tutti().subList(fotografiaService.tutti().size()/2, fotografiaService.tutti().size()));
		return "index";
	}

	@GetMapping(value="/login")
	public String login(){

		return "login";
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
		String email = details.getUsername();
		model.addAttribute("username", email);
		model.addAttribute("funzionario", funzionarioService.funzionarioPerEmail(details.getUsername()));
		model.addAttribute("role", role);
		model.addAttribute("richieste", richiestaService.tutti());
		return "admin";
	}
	@PostMapping(value= "/admin")
  public String richiestaGestita(Model model,   @RequestParam(value = "check") Long id){
	  richiestaService.setCheckedTrue(id);

	  return "admin";
  }
}
