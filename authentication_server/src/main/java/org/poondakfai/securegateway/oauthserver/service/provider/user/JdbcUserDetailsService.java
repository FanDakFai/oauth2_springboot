package org.poondakfai.securegateway.oauthserver.service.provider.user;


import javax.sql.DataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.poondakfai.securegateway.oauthserver.model.SystemUser;
import org.poondakfai.securegateway.oauthserver.repository.UserRepository;


@Service
public class JdbcUserDetailsService implements UserDetailsService/*, UserRegistrationService*/ {
  public static final String SYSTEM_USER_NAME = "system";

  //private final JdbcTemplate jdbcTemplate;

  @Autowired
  private UserRepository userRepository;


  public JdbcUserDetailsService() {
  }

/*  public JdbcUserDetailsService(DataSource dataSource) {
    Assert.notNull(dataSource, "DataSource required");
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.fakeData = new User();
  }*/

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (JdbcUserDetailsService.SYSTEM_USER_NAME.compareTo(username) == 0) {
      return SystemUser.getSystemUser();
    }

    UserDetails result = this.userRepository.findByUsername(username);
    if (result == null) {
      throw new UsernameNotFoundException("User not found " + username);
    }
    return result;
  }
}


