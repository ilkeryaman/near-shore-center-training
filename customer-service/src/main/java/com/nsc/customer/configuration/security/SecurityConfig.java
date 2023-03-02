package com.nsc.customer.configuration.security;

import com.nsc.customer.configuration.security.handler.CustomerKeycloakAuthenticationFailureHandler;
import com.nsc.customer.enums.security.Role;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
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
    private CustomerKeycloakAuthenticationFailureHandler customerKeycloakAuthenticationFailureHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Bean
    @Override
    protected KeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter() throws Exception {
        KeycloakAuthenticationProcessingFilter filter = new KeycloakAuthenticationProcessingFilter(authenticationManagerBean());
        filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        filter.setAuthenticationFailureHandler(customerKeycloakAuthenticationFailureHandler);
        return filter;
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
                .antMatchers(HttpMethod.GET, "/customer-api/customers**").hasAnyRole(Role.CUSTOMER_LISTER.getValue(), Role.CUSTOMER_CREATOR.getValue())
                .antMatchers(HttpMethod.GET, "/customer-api/customers/**").hasAnyRole(Role.CUSTOMER_LISTER.getValue(), Role.CUSTOMER_CREATOR.getValue())
                .antMatchers(HttpMethod.POST, "/customer-api/customers**").hasRole(Role.CUSTOMER_CREATOR.getValue())
                .antMatchers(HttpMethod.DELETE, "/customer-api/customers/**").hasRole(Role.CUSTOMER_CREATOR.getValue())
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customerKeycloakAuthenticationFailureHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(authIgnoredURLs);
    }
}
