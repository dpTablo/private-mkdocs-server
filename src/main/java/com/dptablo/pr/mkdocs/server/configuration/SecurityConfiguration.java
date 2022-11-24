package com.dptablo.pr.mkdocs.server.configuration;

import com.dptablo.pr.mkdocs.server.security.DefaultLoginSuccessHandler;
import com.dptablo.pr.mkdocs.server.security.DefaultLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    @Qualifier("defaultAuthenticationProvider")
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().mvcMatchers(
            "/",
            "/login",
            "/logout",
            "/perform_login"
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()

                .and().csrf().disable()

                .sessionManagement(configurer ->
                        configurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

                .authorizeHttpRequests()
//                    .mvcMatchers("/api/**")
//                        .hasRole("USER")
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .anyRequest().authenticated()

                .and().formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/api/auth/login")
                    .passwordParameter("password")
                    .usernameParameter("userId")
                    .successHandler(loginSuccessHandler())
                .and().logout()
                    .logoutUrl("/api/auth/logout")
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler(logoutSuccessHandler())

                .and().userDetailsService(userDetailsService)
                    .authenticationProvider(authenticationProvider);

        return http.build();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new DefaultLogoutSuccessHandler();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new DefaultLoginSuccessHandler();
    }
}
