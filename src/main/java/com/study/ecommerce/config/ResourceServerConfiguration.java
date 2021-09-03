package com.study.ecommerce.config;


import com.study.ecommerce.config.handler.AuthenticationEntryPointCustom;
import com.study.ecommerce.config.handler.OAuth2AccessDeniedHandlerCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//    private static final Logger LOGGER = Logger.getLogger(ResourceServerConfig.class);
    @Value("${config.oauth2.publicKey}")
    private String publicKey;

    @Value("${config.oauth2.privateKey}")
    private String privateKey;

    @Autowired
    private JwtTokenStore jwtTokenStore;

    @Autowired
    DefaultTokenServices tokenServices;

    @Autowired
    JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    AuthenticationEntryPointCustom authenticationEntryPointCustom;

    @Autowired
    OAuth2AccessDeniedHandlerCustom oauth2AccessDeniedHandlerCustom;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("resource_id")
                .tokenServices(tokenServices)
                .tokenStore(jwtTokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
             .anonymous().disable()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPointCustom)
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
                    , "/actuator/**"
                    , "/api-docs/**"
             )
            .permitAll()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling().accessDeniedHandler(oauth2AccessDeniedHandlerCustom);
    }
}
