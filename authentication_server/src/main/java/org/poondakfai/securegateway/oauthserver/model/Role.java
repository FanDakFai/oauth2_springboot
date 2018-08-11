package org.poondakfai.securegateway.oauthserver.model;


import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


@Entity
@IdClass(RolePK.class)
public class Role implements GrantedAuthority {
  static private final String ROLE_PREFIX = "ROLE_";
  static final long serialVersionUID = 2L;

  @Id
  private String role;

  @Id
  @ManyToOne
  @JoinColumn(name = "uid")
  private User user;


  public Role(String role) {
    this.role = role;
  }

  public Role() {
    this("");
  }

  @Override
  public String getAuthority() {
    return ROLE_PREFIX + this.getRole();
  }

  public String getRole() {
    return this.role;
  }


  public User getUser() {
    return this.user;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
