package it.uniroma3.authtest.controller;


import it.uniroma3.authtest.Utils;
import it.uniroma3.authtest.model.Cliente;
import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.model.Richiesta;
import it.uniroma3.authtest.service.AlbumService;
import it.uniroma3.authtest.service.ClienteService;
import it.uniroma3.authtest.service.FotografiaService;
import it.uniroma3.authtest.service.RichiestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RichiestaController {
  @Autowired
  private ClienteValidator clienteValidator;

  @Autowired
  private RichiestaService richiestaService;

  @Autowired
  private RichiestaValidator richiestaValidator;
  @Autowired
  private FotografiaService fotografiaService;
  @Autowired
  private ClienteService clienteService;


  @RequestMapping({"/inserisciFotoRichiesta"})
  public String listProductHandler(HttpServletRequest request, Model model, //
                                   @RequestParam(value = "code") Long code) {

    Fotografia foto = null;
    if (code != null) {
      foto = fotografiaService.cercaPerId(code);
    }
    if (foto != null) {

      // Cart info stored in Session.
      Richiesta cartInfo = Utils.getCartInSession(request);

      cartInfo.addFoto(foto);
    }
    // Redirect to shoppingCart page.
    return "redirect:/shoppingCart";
  }

  @RequestMapping({"/rimuoviFotoRichiesta"})
  public String removeProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "code", defaultValue = "") Long code) {
    Fotografia product = null;
    if (code != null) {
      product = fotografiaService.cercaPerId(code);
    }
    if (product != null) {

      // Cart Info stored in Session.
      Richiesta cartInfo = Utils.getCartInSession(request);


      cartInfo.removeFoto(product);

    }
    // Redirect to shoppingCart page.
    return "redirect:/shoppingCart";
  }

  // GET: Show Cart
  @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
  public String shoppingCartHandler(HttpServletRequest request, Model model) {
    Richiesta myCart = Utils.getCartInSession(request);

    model.addAttribute("richiesta", myCart);
    model.addAttribute("fotografie",myCart.getFotografie());
    return "shoppingCart";
  }

  // GET: Enter customer information.
  @RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.GET)
  public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

    Richiesta cartInfo = Utils.getCartInSession(request);

    // Cart is empty.
    if (cartInfo.isEmpty()) {

      // Redirect to shoppingCart page.
      return "redirect:/shoppingCart";
    }

    Cliente customerInfo = cartInfo.getCliente();
    if (customerInfo == null) {
      customerInfo = new Cliente();
    }

    model.addAttribute("cliente", customerInfo);

    return "shoppingCartCustomer";
  }

  // POST: Save customer information.
  @RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.POST)
  public String shoppingCartCustomerSave(HttpServletRequest request, //
                                      //
                                         @ModelAttribute("cliente") @Validated Cliente cliente,    Model model,
                                         BindingResult result) {

clienteValidator.validate(cliente,result);
    if (result.hasErrors()) {
      cliente.setValid(false);  // Forward to reenter customer info.

      return "shoppingCartCustomer";
    }

    cliente.setValid(true);
    Richiesta cartInfo = Utils.getCartInSession(request);
    cartInfo.setCliente(cliente);

    // Redirect to Confirmation page.
    return "redirect:/shoppingCartConfirmation";
  }



  // POST: Send Cart (Save).
  @RequestMapping(value = {"/shoppingCartConfirmation"})
  // Avoid UnexpectedRollbackException (See more explanations)
  @Transactional(propagation = Propagation.NEVER)
  public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
    Richiesta cartInfo = Utils.getCartInSession(request);
    if (cartInfo.isEmpty()) {
      // Redirect to shoppingCart page.
      return "redirect:/shoppingCart";
    } else if (!cartInfo.isValidCustomer()) {
      // Enter customer info.
      return "redirect:/shoppingCartCustomer";
    }

      clienteService.inserisci(cartInfo.getCliente());
      richiestaService.inserisci(cartInfo);

    // Remove Cart In Session.
    Utils.removeCartInSession(request);


    model.addAttribute("fotografie1", fotografiaService.tutti().subList(0, fotografiaService.tutti().size()/2));
    model.addAttribute("fotografie2", fotografiaService.tutti().subList(fotografiaService.tutti().size()/2, fotografiaService.tutti().size()));

    // Redirect to successful page.
    return "index";
  }

}

