package org.poondakfai.securegateway.oauthserver.model;


import java.io.Serializable;


public class RolePK implements Serializable {
  static final long serialVersionUID = 3L;

  protected String role;

  protected User user;

  public RolePK() {
  }

  public RolePK(String role, User user) {
    this.role = role;
    this.user = user;
  }

  @Override
  public int hashCode() {
    return this.user.getUsername().hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (o instanceof RolePK) {
      RolePK rolePk = (RolePK) o;
      return (this.user.getUsername().compareTo(rolePk.user.getUsername()) == 0
        && this.role.compareTo(rolePk.role) == 0);
    }
    return false;
  }
}

