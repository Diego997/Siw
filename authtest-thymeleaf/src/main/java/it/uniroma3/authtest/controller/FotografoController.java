package it.uniroma3.authtest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.model.Fotografo;
import it.uniroma3.authtest.service.FotografoService;
import it.uniroma3.authtest.service.FunzionarioService;

@Controller
public class FotografoController {

	@Autowired
	private FotografoService fotografoService;
	
	@Autowired
	private FotografoValidator fotografoValidator;
	
	@Autowired
	private FunzionarioService funzionarioService;
	
	@GetMapping("/addfotografo")
	public String addFotografo(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("funzionario", funzionarioService.funzionarioPerEmail(details.getUsername()));
		model.addAttribute("fotografo", new Fotografo());
		return "addfotografo";
	}
	
	@PostMapping("/addfotografo")
	public String uploadFotografo(@RequestParam("imgFile") MultipartFile imageFile, @Valid @ModelAttribute("fotografo") Fotografo fotografo, Model model, BindingResult bindingResult ){
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("funzionario", funzionarioService.funzionarioPerEmail(details.getUsername()));
		this.fotografoValidator.validate(fotografo, bindingResult);
		if (!bindingResult.hasErrors() && imageFile.getSize()>0) {
            fotografoService.salva(imageFile, fotografo);
            model.addAttribute("fotografo", fotografo);
            return "admin";
        }
		else {
			return "addfotografo";
		}
	}
	
	
	
	@GetMapping("/fotografo/{id}")
	public String showAlbumImage(@PathVariable Long id, Model model) {
		
		Fotografo fotografo = fotografoService.trovaById(id);

		model.addAttribute("fotografo", fotografo);
		model.addAttribute("album", fotografo.getAlbum());
		return "fotografo";
	}

}
