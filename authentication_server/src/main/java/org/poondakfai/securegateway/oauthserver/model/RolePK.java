package org.poondakfai.securegateway.oauthserver.model;


import java.io.Serializable;


public class RolePK implements Serializable {
  static final long serialVersionUID = 3L;

  protected String authority;

  protected User user;

  public RolePK() {
  }

  public RolePK(String authority, User user) {
    this.authority = authority;
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
        && this.authority.compareTo(rolePk.authority) == 0);
    }
    return false;
  }
}

