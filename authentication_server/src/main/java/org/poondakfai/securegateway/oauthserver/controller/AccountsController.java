package org.poondakfai.securegateway.oauthserver.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.ModelMap;


@Controller
public class AccountsController {
  public static final String ACCOUNTS_PAGE_URL = "/accounts";

  @RequestMapping(ACCOUNTS_PAGE_URL)
  public String authorize(ModelMap model) {
    model.addAttribute("message", "Welcome to authorization server Account page. This page is under-construct!");
    return "accounts";
  }
}


