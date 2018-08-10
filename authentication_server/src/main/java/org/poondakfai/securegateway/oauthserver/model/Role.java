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
  static final long serialVersionUID = 2L;

  @Id
  private String authority;

  @Id
  @ManyToOne
  @JoinColumn(name = "uid")
  private User user;


  public Role(String authority) {
    this.authority = authority;
  }

  public Role() {
    this("");
  }

  @Override
  public String getAuthority() {
    return this.authority;
  }

  public User getUser() {
    return this.user;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
