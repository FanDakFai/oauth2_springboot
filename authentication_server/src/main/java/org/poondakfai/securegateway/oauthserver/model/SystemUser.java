package org.poondakfai.securegateway.oauthserver.model;


import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import static org.poondakfai.securegateway.oauthserver.service.provider.user.JdbcUserDetailsService.SYSTEM_USER_NAME;


public final class SystemUser implements UserDetails {
  static final long serialVersionUID = 1L;

  static final private SystemUser singletonSystemUser;

  private final String password = "{noop}dorakido";
  private final Collection<Role> authorities;

  private SystemUser() {
    List<Role> authorities = Arrays.asList(new Role("SYSTEM")
      // SystemUser is local and private. It should not be used for token
      // retrieving via client
      // @TODO remove all of below roles after creating enumarator for them
      // ,new Role("USER")       // oauth: standard scope
      // ,new Role("EX_USER")    // oauth: advance scope
      // ,new Role("CFG_USER")   // oauth: admin scope
    );
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
    return SYSTEM_USER_NAME;
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
    singletonSystemUser = new SystemUser();
  }
}


