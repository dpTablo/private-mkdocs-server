package com.dptablo.pr.mkdocs.server.security;

import com.dptablo.pr.mkdocs.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultAuthenticationProvider implements AuthenticationProvider {
    @Qualifier("defaultUserService")
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            var user = userService.getMatchedUser(authentication.getName(), authentication.getCredentials().toString())
                    .orElseThrow(NullPointerException::new);
            return new NgeneBioAuthentication(user);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
