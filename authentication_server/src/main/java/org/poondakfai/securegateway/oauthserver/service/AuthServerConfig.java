package org.poondakfai.securegateway.oauthserver.service;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;

  @Autowired
  private DataSource dataSource;

 
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
    clients.jdbc(dataSource)
/*      // standard dns
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
      .scopes(ADVANCE, STANDARD)
      .autoApprove(false)
      .and()
      // administrator
      .withClient("adminclient")
      .redirectUris("https://localhost:8888/sysconfig")
      .secret("{noop}M0uD.mY@ ")
      .authorizedGrantTypes("password", "authorization_code")
      .scopes(ADMIN, ADVANCE, STANDARD)
      .autoApprove(false)*/;
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
    return new JdbcTokenStore(dataSource);
  }
}


