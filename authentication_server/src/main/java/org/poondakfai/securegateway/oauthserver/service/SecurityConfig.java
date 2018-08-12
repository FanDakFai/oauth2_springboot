package org.poondakfai.securegateway.oauthserver.service;


import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.BeanIds;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.poondakfai.securegateway.oauthserver.service.provider.user.JdbcUserDetailsService;
import org.poondakfai.securegateway.oauthserver.model.Roles;
import static org.poondakfai.securegateway.oauthserver.controller.AccountsController.ACCOUNTS_PAGE_URL;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private Environment env;

  @Value("classpath:schema.sql")
  private Resource schemaScript;

  private DataSource dataSource;

  private DataSourceInitializer initializer;

  @Autowired
  private JdbcUserDetailsService jdbcUserDetailsService;


  @Override
  protected void configure(AuthenticationManagerBuilder auth)
    throws Exception {
    //auth.userDetailsService(new JdbcUserDetailsService(dataSource()));
    auth.userDetailsService(jdbcUserDetailsService);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean()
    throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
      authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/oauth/*").hasRole(Roles.USER.toString())
        .antMatchers(ACCOUNTS_PAGE_URL).hasRole(Roles.SYSTEM.toString())
        .anyRequest().authenticated()
        .and()
      .formLogin()
        .permitAll()
    ;
  }

  @Bean
  public DataSource dataSource() {
    if (this.dataSource == null) {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
      dataSource.setUrl(env.getProperty("jdbc.url"));
      dataSource.setUsername(env.getProperty("jdbc.user"));
      dataSource.setPassword(env.getProperty("jdbc.pass"));
      this.dataSource = dataSource;
    }
    return this.dataSource;
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
    if (this.initializer == null) {
      DataSourceInitializer initializer = new DataSourceInitializer();
      initializer.setDataSource(dataSource);
      initializer.setDatabasePopulator(databasePopulator());
      this.initializer = initializer;
    }
    return this.initializer;
  }

  private DatabasePopulator databasePopulator() {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(schemaScript);
    return populator;
  }
}


