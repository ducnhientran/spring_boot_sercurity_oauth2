//package com.study.ecommerce.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import springfox.documentation.builders.OAuthBuilder;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//public class OAuthSwaggerSecurityConfig_old {
//
//
//    @Value("${config.oauth2.accessTokenUri}")
//    private String accessTokenUri;
//
//    @Value("${config.oauth2.userAuthorizationUri}")
//    private String accessAuthorizeUri;
//
//    @Value("${spring.oauth2.client.id}")
//    private String clientId;
//    @Value("${spring.oauth2.client.secret}")
//    private String clientPass;
//
//    private String host = "http://localhost:8082";
//
//
//
////    @Value("${host}")
////    private String host;
//
//    private static final String SECURITY_SCHEME_NAME = "spring_oauth";
////    private static final String CLIENT_ID = "spring-security-oauth2-read-write-client";
////    private static final String CLIENT_SECRET = "spring-security-oauth2-read-write-client-password1234";
//
//    @Bean
//    public SecurityConfiguration securityInfo() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId(clientId)
//                .clientSecret(clientPass)
//                .scopeSeparator(" ")
//                .build();
//    }
//
//    private AuthorizationScope[] scopes() {
//        AuthorizationScope[] scopes = {
//                new AuthorizationScope("read", "for read operations"),
//                new AuthorizationScope("write", "for write operations")
//        };
//
//        return scopes;
//    }
//
//    private List<SecurityReference> securityReferences() {
//        return Collections.singletonList(new SecurityReference(SECURITY_SCHEME_NAME, scopes()));
//    }
//
//    @Bean
//    public SecurityScheme securityScheme() {
//        LoginEndpoint loginEndpoint = new LoginEndpoint(host + "/oauth/authorize");
//        GrantType grantType = new ImplicitGrant(loginEndpoint, OAuth2AccessToken.ACCESS_TOKEN);
//
//        SecurityScheme oauth = new OAuthBuilder().name(SECURITY_SCHEME_NAME)
//                .grantTypes(Collections.singletonList(grantType))
//                .scopes(Arrays.asList(scopes()))
//                .build();
//
//        return oauth;
//    }
//
//    @Bean
//    public SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(securityReferences())
//
//                // .forPaths(Predicates.alwaysTrue())
//                .build();
//    }
//}
//
//
