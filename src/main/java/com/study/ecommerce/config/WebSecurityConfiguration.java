package com.study.ecommerce.config;

import com.study.ecommerce.oauth.UserDetailsServiceCustom;
import com.study.ecommerce.repository.EcommerceUserRepository;
//import com.study.ecommerce.service.impl.DefaultAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceCustom userDetailsServiceCustom;

    @Autowired
    private EcommerceUserRepository ecommerceUserRepository;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceCustom).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//       auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//       //auth.authenticationProvider(new DefaultAuthenticationProvider(ecommerceUserRepository));
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/login", "/swagger-ui/**"
                , "/swagger-ui.html/**"
                , "/v3/api-docs/swagger-config"
                , "/v3/api-docs"
                , "/v2/api-docs"
                , "/v1/api-docs"
                , "/webjars/**"
                , "/swagger-resources/**"
                , "/actuator/**"
                , "/api-docs/**");
    }

}
