package com.study.ecommerce.config;

import com.study.ecommerce.jwt.JwtUserDetailsService;
import com.study.ecommerce.repository.EcommerceUserRepository;
//import com.study.ecommerce.service.impl.DefaultAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint  jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private EcommerceUserRepository ecommerceUserRepository;


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
       //auth.authenticationProvider(new DefaultAuthenticationProvider(ecommerceUserRepository));
    }

//    @Bean
//    public JwtRequestFilter jwtRequestFilter() {
//        return new JwtRequestFilter();
//    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(
//                "/v2/api-docs",
//                "/configuration/ui/**",
//                "/swagger-resources/**",
//                "/configuration/security/**",
//                "/swagger-ui.html",
//                "/webjars/**");
//    }

    //    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(
//                "/v2/api-docs",
//                "/configuration/ui/**",
//                "/swagger-resources/**",
//                "/configuration/security/**",
//                "/swagger-ui.html",
//                "/webjars/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
             .and()
             .authorizeRequests()
             .antMatchers("/login", "/swagger-ui/**"
                    , "/swagger-ui.html/**"
                    , "/v3/api-docs/swagger-config"
                    , "/v3/api-docs"
                    , "/v2/api-docs"
                    , "/v1/api-docs"
                    , "/webjars/**"
                    , "/swagger-resources/**"
             )
            .permitAll();
//            .anyRequest()
//            .authenticated()
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        //http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
