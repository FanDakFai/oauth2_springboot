package org.poondakfai.securegateway.oauthserver.controller.fragment;


import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.ModelMap;
import org.poondakfai.securegateway.oauthserver.model.User;
import org.poondakfai.securegateway.oauthserver.repository.UserRepository;


public final class UserForm {
  private UserRepository userRepository;

  private Configuration configuration;


  public final class Configuration {
    private UserRepository userRepository;
    private String commandObjectName = "cmdobj";
    private String addUserHttpParam = "adduser";


    public String getCommandObjectName() {
      return this.commandObjectName;
    }

    public String getAddUserHttpParam() {
      return this.addUserHttpParam;
    }

    public void setCommandObjectName(String commandObjectName) {
      this.commandObjectName = commandObjectName;
    }

    public void setAddUserHttpParam(String addUserHttpParam) {
      this.addUserHttpParam = addUserHttpParam;
    }
  };

  public final class ResponseObject {
    private final UserRepository userRepository;
    private final CommandObject cmdobj;
    private final Configuration cfg;
    private String formAction = "";


    public ResponseObject(UserForm userForm, CommandObject cmdobj) {
      this.userRepository = userForm.getUserRepository();
      this.cfg = userForm.getConfiguration();
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

    public Configuration getCfg() {
      return this.cfg;
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


  public UserForm(UserRepository userRepository) {
    this.userRepository = userRepository;
    configuration = new Configuration();
  }

  public UserRepository getUserRepository() {
    return this.userRepository;
  }

  public Configuration getConfiguration() {
    return this.configuration;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  public void showUsers(ModelMap model, final HttpServletRequest req) {
    String cmdobjName = this.getConfiguration().getCommandObjectName();
    boolean doesCmdobjExist = model.containsAttribute(cmdobjName);
    CommandObject cmdobj = doesCmdobjExist ?
      (CommandObject)model.get(cmdobjName) :
      new CommandObject();
    new ResponseObject(this, cmdobj);
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


