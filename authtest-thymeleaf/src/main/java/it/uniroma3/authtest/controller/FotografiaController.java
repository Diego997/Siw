package it.uniroma3.authtest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.authtest.model.Album;
import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.service.AlbumService;
import it.uniroma3.authtest.service.FotografiaService;
import it.uniroma3.authtest.service.FunzionarioService;

@Controller
public class FotografiaController {

	@Autowired
	private FotografiaService fotografiaService;

	@Autowired
	private FotografiaValidator fotografiaValidator;

	@Autowired
	private FunzionarioService funzionarioService;

	@Autowired
	private AlbumService albumService;



	@RequestMapping("/images")
	public String showFotografie(Model model){
		model.addAttribute("fotografie1", fotografiaService.tutti().subList(0, fotografiaService.tutti().size()/2));
		model.addAttribute("fotografie2", fotografiaService.tutti().subList(fotografiaService.tutti().size()/2, fotografiaService.tutti().size()));
		return "index";
	}
	@GetMapping("/fotografia/{id}")
	public String showFotografiaImage(@PathVariable Long id, Model model) {

		Fotografia fotografia = fotografiaService.cercaPerId(id);

		model.addAttribute("fotografia", fotografia);
		return "fotografia";
	}

	@GetMapping("/addfotografia")
	public String addFotografia(Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("funzionario", funzionarioService.funzionarioPerEmail(details.getUsername()));
		model.addAttribute("fotografia", new Fotografia());
		model.addAttribute("album", albumService.tutti());
		return "addfotografia";
	}
//<a
//                   href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">
//                       Buy Now</a>
	@PostMapping("/addfotografia")
	public String uploadImage(@Valid @ModelAttribute("fotografia") Fotografia fotografia, @RequestParam("imgFile") MultipartFile imageFile, Model model, BindingResult bindingResult ){
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("funzionario", funzionarioService.funzionarioPerEmail(details.getUsername()));
		this.fotografiaValidator.validate(fotografia, bindingResult);
		if(!(imageFile.getSize()>0))
			bindingResult.reject("foto");
		
		if (!bindingResult.hasErrors() && imageFile.getSize()>0) {
			Album album = fotografia.getAlbum();
			fotografiaService.salvaFoto(imageFile, fotografia);
			albumService.salva(album);
			model.addAttribute("fotografia", fotografia);
			return "admin";
		}
		else {
			model.addAttribute("album", albumService.tutti());
			return "addfotografia";
		}
	}

}
