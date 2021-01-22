package com.bjjmaster.backendapplication.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()
                // allow swaggger-ui
                .antMatchers("/swagger-ui/**").permitAll()//
                .antMatchers("/v3/api-docs/**").permitAll()//
                .antMatchers("/api-docs.yaml").permitAll()//
                // allow h2 console
                .antMatchers("/h2-console/**/**").permitAll()
                .antMatchers("/public/**").permitAll()//
                // Disallow everything else..
                .anyRequest().authenticated();


        // Apply JWT
//        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }
}
