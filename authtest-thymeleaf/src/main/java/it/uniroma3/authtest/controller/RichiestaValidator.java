package it.uniroma3.authtest.controller;

import it.uniroma3.authtest.model.Richiesta;
import it.uniroma3.authtest.service.RichiestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RichiestaValidator implements Validator{
  @Autowired
  private RichiestaService fotografoService;

  @Override
  public boolean supports(Class<?> clazz) {
    return Richiesta.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente", "required");
    if (this.fotografoService.alreadyExists((Richiesta)target)) {
      errors.reject("duplicato");
    }
  }
}
