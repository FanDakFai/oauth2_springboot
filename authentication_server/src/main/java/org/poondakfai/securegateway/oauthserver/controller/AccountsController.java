package org.poondakfai.securegateway.oauthserver.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.poondakfai.securegateway.oauthserver.controller.fragment.UserForm;
import org.poondakfai.securegateway.oauthserver.controller.fragment.UserForm.CommandObject;
import org.poondakfai.securegateway.oauthserver.controller.fragment.UserForm.ResponseObject;


@Controller
public class AccountsController {
  public static final String ACCOUNTS_PAGE_URL = "/accounts";

  private UserForm userForm;


  public AccountsController(@Autowired UserForm userForm) {
    this.userForm = userForm;
    this.userForm.setCommandObjectName("formCmdobj01");
  }

  @RequestMapping(value = ACCOUNTS_PAGE_URL, method=RequestMethod.GET)
  public String listUsers(ModelMap model, final HttpServletRequest req) {
    userForm.showUsers(model, req);
    return "accounts";
  }

  @RequestMapping(value = ACCOUNTS_PAGE_URL, method=RequestMethod.POST, params={"adduser"})
  public String addUser(final CommandObject cmdobj, ModelMap model, final BindingResult bindingResult, final HttpServletRequest req) {
    userForm.addUser(cmdobj);
    model.addAttribute(this.userForm.getCommandObjectName(), cmdobj);
    userForm.showUsers(model, req);
    return "accounts";
  }
}


