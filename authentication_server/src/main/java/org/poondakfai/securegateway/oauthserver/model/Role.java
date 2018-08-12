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
  private int id;

  @Id
  @ManyToOne
  @JoinColumn(name = "uid")
  private User user;


  public Role(Roles role) {
    this.id = role.getId();
  }

  public Role() {
    this(Roles.UNKNOWN);
  }

  @Override
  public String getAuthority() {
    return Roles.getRole(this.getId()).getAuthority();
  }

  public int getId() {
    return this.id;
  }

  public User getUser() {
    return this.user;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUser(User user) {
    this.user = user;
  }
}


