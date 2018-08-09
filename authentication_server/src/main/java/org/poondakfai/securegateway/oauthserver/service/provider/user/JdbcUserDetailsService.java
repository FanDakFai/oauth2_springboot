package org.poondakfai.securegateway.oauthserver.service.provider.user;


import javax.sql.DataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.poondakfai.securegateway.oauthserver.model.User;
import org.springframework.util.Assert;
import org.springframework.jdbc.core.JdbcTemplate;


public class JdbcUserDetailsService implements UserDetailsService/*, UserRegistrationService*/ {
  private final JdbcTemplate jdbcTemplate;

  private final User fakeData;


  public JdbcUserDetailsService(DataSource dataSource) {
    Assert.notNull(dataSource, "DataSource required");
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.fakeData = new User();
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.fakeData;
  }
}


