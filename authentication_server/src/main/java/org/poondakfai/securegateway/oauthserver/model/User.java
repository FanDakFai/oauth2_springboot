package org.poondakfai.securegateway.oauthserver.model;


import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;


//@Entity
public class User implements UserDetails {
  List<Role> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.authorities == null) {
      this.authorities = new ArrayList<Role>();
      this.authorities.add(new Role("USER"));
    }
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return "{noop}123";
  }

  @Override
  public String getUsername() {
    return "john";
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}


