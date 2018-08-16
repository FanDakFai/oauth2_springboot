package org.poondakfai.securegateway.oauthserver.controller.fragment;


import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.poondakfai.securegateway.oauthserver.model.User;
import org.poondakfai.securegateway.oauthserver.repository.UserRepository;


@Component
public final class UserForm {
  @Autowired
  private UserRepository userRepository;

  private String commandObjectName = "cmdobj";


  public final class ResponseObject {
    private final UserRepository userRepository;
    private final CommandObject cmdobj;
    private String formAction = "";

    public ResponseObject(UserRepository userRepository, CommandObject cmdobj) {
      this.userRepository = userRepository;
      this.cmdobj = cmdobj;
      this.cmdobj.setResp(this);
    }

    public CommandObject getCmdobj() {
      return this.cmdobj;
    }

    public String getFormAction() {
      return this.formAction;
    }

    public Iterable<User>getDatasource() {
      return this.userRepository.findAll();
    }

    public void setFormAction(String formAction) {
      this.formAction = formAction;
    }
  }

  public final class CommandObject {
    private String username;

    private ResponseObject resp;


    public String getUsername() {
      return this.username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public ResponseObject getResp() {
      return this.resp;
    }

    public void setResp(ResponseObject resp) {
      this.resp = resp;
    }
  }


  public UserForm() {
  }

  public String getCommandObjectName() {
    return this.commandObjectName;
  }

  public void setCommandObjectName(String commandObjectName) {
    this.commandObjectName = commandObjectName;
  }

  public void showUsers(ModelMap model, final HttpServletRequest req) {
    String cmdobjName = this.getCommandObjectName();
    boolean doesCmdobjExist = model.containsAttribute(cmdobjName);
    CommandObject cmdobj = doesCmdobjExist ?
      (CommandObject)model.get(cmdobjName) :
      new CommandObject();
    new ResponseObject(this.userRepository, cmdobj);
    model.addAttribute(cmdobjName, cmdobj);
    cmdobj.getResp().setFormAction(req.getServletPath());
  }

  public void addUser(final CommandObject cmdobj) {
    User user = new User();
    user.setUsername(cmdobj.getUsername());
    user.setPassword("{noop}123");
    this.userRepository.save(user);
  }
}


