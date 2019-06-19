package it.uniroma3.authtest;

import javax.servlet.http.HttpServletRequest;


import it.uniroma3.authtest.model.Richiesta;

public class Utils {

  // Products in Cart, stored in Session.
  public static Richiesta getCartInSession(HttpServletRequest request) {

    // Get Cart from Session.
    Richiesta cartInfo = (Richiesta) request.getSession().getAttribute("myCart");

    // If null, create it.
    if (cartInfo == null) {
      cartInfo = new Richiesta();

      // And store to Session.
      request.getSession().setAttribute("myCart", cartInfo);
    }

    return cartInfo;
  }

  public static void removeCartInSession(HttpServletRequest request) {
    request.getSession().removeAttribute("myCart");
  }

  public static void storeLastOrderedCartInSession(HttpServletRequest request, Richiesta cartInfo) {
    request.getSession().setAttribute("lastOrderedCart", cartInfo);
  }

  public static Richiesta getLastOrderedCartInSession(HttpServletRequest request) {
    return (Richiesta) request.getSession().getAttribute("lastOrderedCart");
  }

}
