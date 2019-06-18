package it.uniroma3.authtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.authtest.model.Fotografo;
import it.uniroma3.authtest.service.FotografoService;

@Component
public class FotografoValidator implements Validator{
	@Autowired
	private FotografoService fotografoService;

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		if (this.fotografoService.alreadyExists((Fotografo)o)) {
			errors.reject("duplicato");
		}
	}


	@Override
	public boolean supports(Class<?> aClass) {
		return Fotografo.class.equals(aClass);
	}
}
