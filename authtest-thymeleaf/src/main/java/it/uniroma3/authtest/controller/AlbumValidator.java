package it.uniroma3.authtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.authtest.model.Album;
import it.uniroma3.authtest.service.AlbumService;

@Component
public class AlbumValidator implements Validator{
	@Autowired
	private AlbumService albumService;

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		if (this.albumService.alreadyExists((Album)o)) {
			errors.reject("duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

}
