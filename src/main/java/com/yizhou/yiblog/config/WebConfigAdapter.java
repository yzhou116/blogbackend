package com.yizhou.yiblog.config;

import com.yizhou.yiblog.service.impl.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfigAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**")
                .permitAll().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/googlelogin", "/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/googlelogin")
                .userInfoEndpoint()
                .userService(oauthUserService);
    }
}