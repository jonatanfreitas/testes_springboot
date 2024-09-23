package com.financeiroteste.config.token;

import java.util.HashMap;
import java.util.Map;
import com.financeiroteste.security.UsuarioSistema;
import com.financeiroteste.token.RefreshTokenCookiePreProcessorFilter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
@Slf4j
@SuppressWarnings("deprecation")
public class CustomTokenEnhancer implements TokenEnhancer{
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();
		
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("nome", usuarioSistema.getUsuario().getNome());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		addInfo.put("contra-senha", "batata frita");
		addInfo.put("userName", usuarioSistema.getUsername());		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		log.info("Log Custon Token Enhancer Value accessToken: "+(accessToken).getScope());
		log.info("Log Custon Token Enhancer Value accessToken: "+((DefaultOAuth2AccessToken) accessToken).getValue());
		return accessToken;
	}
}
