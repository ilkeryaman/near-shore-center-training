package com.nsc.customer.configuration.rest.resttemplate.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Date;

public class CustomerServiceOAuth2ClientContext extends DefaultOAuth2ClientContext {
    @Value("${spring.security.oauth2.client.address-api.expireCorrectionInSeconds}")
    private Long expireCorrectionInSeconds;

    @Override
    public void setAccessToken(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken addressApiAccessToken = new DefaultOAuth2AccessToken(accessToken);
        addressApiAccessToken.setExpiration(arrangeExpirationDate(accessToken.getExpiration()));
        super.setAccessToken(addressApiAccessToken);
    }

    private Date arrangeExpirationDate(Date expiration) {
        return Date.from(expiration.toInstant().minusSeconds(getExpireCorrectionInSeconds()));
    }

    public Long getExpireCorrectionInSeconds() {
        return expireCorrectionInSeconds;
    }
}
