package com.nsc.address.security;

import com.nsc.address.enums.security.Role;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@ConditionalOnProperty(value = "auth.enabled", matchIfMissing = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Value("${auth.ignored.urls}")
    private String[] authIgnoredURLs;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/address-api/addresses**").hasAnyRole(Role.ADDRESS_LISTER.getValue(), Role.ADDRESS_CREATOR.getValue())
                .antMatchers(HttpMethod.GET, "/address-api/addresses/**").hasAnyRole(Role.ADDRESS_LISTER.getValue(), Role.ADDRESS_CREATOR.getValue())
                .antMatchers(HttpMethod.POST, "/address-api/addresses**").hasRole(Role.ADDRESS_CREATOR.getValue())
                .antMatchers(HttpMethod.DELETE, "/address-api/addresses/**").hasRole(Role.ADDRESS_CREATOR.getValue())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(authIgnoredURLs);
    }
}
