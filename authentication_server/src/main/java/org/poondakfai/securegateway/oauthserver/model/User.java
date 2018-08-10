package org.poondakfai.securegateway.oauthserver.model;


import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;


@Entity(name = "Account")
public class User implements UserDetails {
  static final long serialVersionUID = 1L;

  @Id
  private String username;

  @Column(unique = false)
  private String password;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user",
    cascade = {CascadeType.ALL}, orphanRemoval = true)
  private Set<Role> authorities;


  public User() {
    this.authorities = new HashSet<Role>();
  }

  @Override
  public Collection<Role> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
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

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAuthorities(Set<Role> authorities) {
    this.authorities.retainAll(authorities);
    this.authorities.addAll(authorities);
    for (Role role : this.authorities) {
      if (role.getUser() != this) {
        role.setUser(this);
      }
    }
  }
}


