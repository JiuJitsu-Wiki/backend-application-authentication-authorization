package com.bjjmaster.backendapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // TODO: remove in higher environment
        http.headers().frameOptions().sameOrigin();

        // Entry points
        http.authorizeRequests()
                // allow swaggger-ui
                .antMatchers("/swagger-ui/**").permitAll()//
                .antMatchers("/swagger-ui.html").permitAll()//
                .antMatchers("/v3/api-docs/**").permitAll()//
                // allow h2 console
                .antMatchers("/h2-console/**/**").permitAll()
                .antMatchers("/console/**").permitAll()//


                .antMatchers("/public/**").permitAll()//
                .antMatchers("/users/register").permitAll()//
                // Disallow everything else..
                .anyRequest().authenticated();


        // Apply JWT
//        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }
}
