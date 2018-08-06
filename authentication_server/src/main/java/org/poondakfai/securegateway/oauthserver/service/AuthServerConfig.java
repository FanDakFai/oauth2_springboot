package org.poondakfai.securegateway.oauthserver.service;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import static org.poondakfai.securegateway.oauthserver.common.DNSApiScopes.*;


@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
  @Autowired
  private Environment env;    

  @Value("classpath:schema.sql")
  private Resource schemaScript;

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;


  private DatabasePopulator databasePopulator() {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScript(schemaScript);
    return populator;
  }
 
  @Override
  public void configure(
    AuthorizationServerSecurityConfigurer oauthServer) 
    throws Exception {
    oauthServer
      .tokenKeyAccess("permitAll()")
      .checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) 
    throws Exception {
    clients.jdbc(dataSource())
      // standard dns
      .withClient("guest")
      .secret("{noop}guest")
      .authorizedGrantTypes("password", "implicit")
      .scopes(STANDARD)
      .autoApprove(true)
      .redirectUris("http://localhost:8888/dnsstart")
      .and()
      // advance dns
      .withClient("dnsclient")
      .redirectUris("https://localhost:8888/dnsstart")
      .secret("{noop}secret")
      .authorizedGrantTypes("password", "authorization_code", "refresh_token")
      .scopes(ADVANCE)
      .and()
      // administrator
      .withClient("adminclient")
      .redirectUris("https://localhost:8888/sysconfig")
      .secret("{noop}M0uD.mY@ ")
      .authorizedGrantTypes("password", "authorization_code")
      .scopes(ADMIN);
  }

  @Override
  public void configure(
    AuthorizationServerEndpointsConfigurer endpoints)
    throws Exception {
      endpoints
      .tokenStore(tokenStore())
      .authenticationManager(authenticationManager);
  }

  @Bean
  public TokenStore tokenStore() {
    return new JdbcTokenStore(dataSource());
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(databasePopulator());
    return initializer;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
    dataSource.setUrl(env.getProperty("jdbc.url"));
    dataSource.setUsername(env.getProperty("jdbc.user"));
    dataSource.setPassword(env.getProperty("jdbc.pass"));
    return dataSource;
  }   
}


