package com.hackathon._config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by TrungNN on 10/29/2016.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login/**", "/api/**", "/**").permitAll()
//                    .antMatchers("/manager/**").hasRole("manager")
//                    .antMatchers("/admin/**").hasRole("admin")
//                    .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                    .successHandler(authSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.csrf().disable();
    }


}
