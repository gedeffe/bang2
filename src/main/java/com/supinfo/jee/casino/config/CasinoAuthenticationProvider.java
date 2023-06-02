package com.supinfo.jee.casino.config;

import com.supinfo.jee.casino.api.GameApi;
import com.supinfo.jee.casino.api.GameInputDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CasinoAuthenticationProvider implements AuthenticationProvider {

    private final GameApi gameApi;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("try to authenticate {}", authentication);
        Authentication target = null;
        String principal = String.valueOf(authentication.getPrincipal());
        String credentials = String.valueOf(authentication.getCredentials());
        GameInputDto newGame = new GameInputDto(principal, credentials);
        try {
            this.gameApi.authenticate(newGame);
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            target = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
        } catch (FeignException.FeignClientException e) {
            log.error("Unable to authenticate this player {} !", principal, e);
        }
        return target;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
