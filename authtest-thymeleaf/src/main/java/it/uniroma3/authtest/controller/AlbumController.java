package it.uniroma3.authtest.controller;

import java.util.List;

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
import it.uniroma3.authtest.model.Fotografo;
import it.uniroma3.authtest.model.Album;
import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.service.AlbumService;
import it.uniroma3.authtest.service.FotografoService;
import it.uniroma3.authtest.service.FunzionarioService;

@Controller
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@Autowired
	private AlbumValidator albumValidator;

	@Autowired
	private FunzionarioService funzionarioService;

	@Autowired
	private FotografoService fotografoService;

	@GetMapping("/addalbum")
	public String addAlbum(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("funzionario", funzionarioService.funzionarioPerEmail(details.getUsername()));
		model.addAttribute("album", new Album());
		model.addAttribute("fotografi", fotografoService.tutti());
		return "addalbum";
	}

	@PostMapping("/addalbum")
	public String uploadAlbum(@Valid @ModelAttribute("album") Album album, Model model, BindingResult bindingResult ){
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("funzionario", funzionarioService.funzionarioPerEmail(details.getUsername()));
		this.albumValidator.validate(album, bindingResult);
		if (!bindingResult.hasErrors()) {
			Fotografo fotografo = album.getFotografo();
			fotografo.getAlbum().add(album);
			fotografoService.salva(fotografo);
			model.addAttribute("album", album);
			return "admin";
		}
		else {
			model.addAttribute("fotografi", fotografoService.tutti());
			return "addalbum";
		}
	}

	@GetMapping("/album/{id}")
	public String showFotografiaImage(@PathVariable Long id, Model model) {

		Album album = albumService.cercaPerId(id);

		List<Fotografia> fotografie = album.getFotografie();
		model.addAttribute("album", album);
		model.addAttribute("fotografie", fotografie);
		System.out.print(fotografie);
		return "album";
	}

}
