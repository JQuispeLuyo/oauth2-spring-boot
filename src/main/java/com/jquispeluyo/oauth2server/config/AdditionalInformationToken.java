package com.jquispeluyo.oauth2server.config;

import com.jquispeluyo.oauth2server.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdditionalInformationToken implements TokenEnhancer {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        return usuarioRepo.findByUsername(oAuth2Authentication.getName())
                .map(user->{
                    Map<String, Object> info = new HashMap<String, Object>();
                    info.put("firstname",user.getFirstname());
                    info.put("lastname",user.getLastname());
                    ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
                    return oAuth2AccessToken;
                })
                .orElse(oAuth2AccessToken);
    }
}
