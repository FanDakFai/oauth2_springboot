package org.poondakfai.securegateway.oauthserver.model;


import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Entity;


//@Entity
public class Role implements GrantedAuthority {
  private String authority;


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

  public void setAuthority(String authority) {
    this.authority = authority;
  }
}
