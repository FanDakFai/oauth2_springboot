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
import org.poondakfai.securegateway.oauthserver.repository.UserRepository;


@Controller
public class AccountsController {
  public static final String ACCOUNTS_PAGE_URL = "/accounts";

  private UserForm userForm1;
  private UserForm userForm2;


  public AccountsController(@Autowired UserRepository userRepository) {
    this.userForm1 = new UserForm(userRepository);
    //@TODO this should be a formname instead of commandObjectName for abstract setting
    this.userForm1.getConfiguration().setCommandObjectName("formCmdobj01");
    this.userForm1.getConfiguration().setAddUserHttpParam("adduser1");

    this.userForm2 = new UserForm(userRepository);
    this.userForm2.getConfiguration().setCommandObjectName("formCmdobj02");
    this.userForm2.getConfiguration().setAddUserHttpParam("adduser2");
  }

  @RequestMapping(value = ACCOUNTS_PAGE_URL, method=RequestMethod.GET)
  public String listUsers(ModelMap model, final HttpServletRequest req) {
    userForm1.showUsers(model, req);
    userForm2.showUsers(model, req);
    return "accounts";
  }

  @RequestMapping(value = ACCOUNTS_PAGE_URL, method=RequestMethod.POST, params={"adduser1"})
  public String addUser1(final CommandObject cmdobj, ModelMap model, final BindingResult bindingResult, final HttpServletRequest req) {
    System.out.println("adduser1");
    userForm1.addUser(cmdobj);
    model.addAttribute(this.userForm1.getConfiguration().getCommandObjectName(), cmdobj);
    userForm1.showUsers(model, req);
    return "accounts";
  }

  @RequestMapping(value = ACCOUNTS_PAGE_URL, method=RequestMethod.POST, params={"adduser2"})
  public String addUser2(final CommandObject cmdobj, ModelMap model, final BindingResult bindingResult, final HttpServletRequest req) {
    System.out.println("adduser2");
    userForm2.addUser(cmdobj);
    model.addAttribute(this.userForm2.getConfiguration().getCommandObjectName(), cmdobj);
    userForm2.showUsers(model, req);
    return "accounts";
  }
}


