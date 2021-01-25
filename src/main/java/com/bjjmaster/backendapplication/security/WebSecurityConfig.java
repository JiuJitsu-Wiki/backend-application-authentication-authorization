package com.bjjmaster.backendapplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtTokenProvider jwtTokenProvider;

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // This is for h2-console and should be removed in higher environment
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
                .antMatchers("/users/login").permitAll()//
                // Disallow everything else..
                .anyRequest().authenticated();


        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }
}
