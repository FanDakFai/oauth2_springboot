package org.poondakfai.securegateway.oauthserver.model;


import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;


public final class SystemUser implements UserDetails {
  static final long serialVersionUID = 1L;
  public static final String USERNAME;
  private static final SystemUser singletonSystemUser;

  private final String password;
  private final Collection<Role> authorities;

  private SystemUser() {
    password = "{noop}dorakido";
    List<Role> authorities = Arrays.asList(new Role(Roles.SYSTEM));
    this.authorities = Collections.unmodifiableList(authorities);
  }

  public Collection<Role> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return USERNAME;
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

  public static final SystemUser getSystemUser() {
    return SystemUser.singletonSystemUser;
  }

  static {
    USERNAME = "system";
    singletonSystemUser = new SystemUser();
  }
}


