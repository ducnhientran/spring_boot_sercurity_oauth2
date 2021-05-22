//package com.study.ecommerce.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import springfox.documentation.builders.OAuthBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//public class OAuthSwaggerSecurityConfig {
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
//    private static final String SECURITY_SCHEME_NAME = "oauth2schema";
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
//    public OAuth securityScheme() {
//        List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
//        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
//        authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
//        authorizationScopeList.add(new AuthorizationScope("write", "access all"));
//
//        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(host + "/oauth/token");
//        List<GrantType> grantTypes = new ArrayList<>();
//        grantTypes.add(grantType);
//        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
//    }
//
////    @Bean
////    public SecurityContext securityContext() {
////        return SecurityContext.builder()
////                .securityReferences(securityReferences())
////
////                // .forPaths(Predicates.alwaysTrue())
////                .build();
////    }
//
//    @Bean
//    public SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/**"))
//                .build();
//    }
//    private List<SecurityReference> defaultAuth() {
//
//        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
//        authorizationScopes[0] = new AuthorizationScope("read", "read all");
//        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
//        authorizationScopes[2] = new AuthorizationScope("write", "write all");
//
//        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
//    }
//
//}
//
//
