//package com.study.ecommerce.service.impl;
//
//import com.study.ecommerce.entity.EcommerceUser;
//import com.study.ecommerce.entity.Permission;
//import com.study.ecommerce.entity.Role;
//import com.study.ecommerce.entity.UserRole;
//import com.study.ecommerce.repository.EcommerceUserRepository;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.ObjectUtils;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@AllArgsConstructor
//@NoArgsConstructor
//public class DefaultAuthenticationProvider implements AuthenticationProvider {
//
//    private EcommerceUserRepository ecommerceUserRepository;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        EcommerceUser user = ecommerceUserRepository.findFirstByUsernameAndDeletedIsFalse(authentication.getName());
//        if(!ObjectUtils.isEmpty(user)) {
//
//            String username = authentication.getName();
//            String password = (String)authentication.getCredentials();
//            if(username.equalsIgnoreCase(user.getUsername()) &&
//                    password.equalsIgnoreCase(user.getPassword())) {
//                Set<Role> roles =  user.getUserRoles().stream().filter(userRole -> userRole.getRole() != null)
//                        .map(UserRole::getRole).collect(Collectors.toSet());
//                return new UsernamePasswordAuthenticationToken(
//                        user.getUsername(),
//                        user.getPassword(),
//                        getListGrantedAuthorityByRoles(roles)
//                );
//            }
//        }
//
//        throw new UsernameNotFoundException("User not found");
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//
//    private Set<GrantedAuthority> getListGrantedAuthorityByRoles(Set<Role> roles) {
//        if (CollectionUtils.isEmpty(roles)) {
//            return Collections.emptySet();
//        } else {
//            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//            for (Role role : roles) {
//                if (!CollectionUtils.isEmpty(role.getPermissions())) {
//                    role.getPermissions().stream().map(Permission::getCode).collect(Collectors.toSet())
//                            .forEach(permissionCode -> {
//                                grantedAuthorities.add(new SimpleGrantedAuthority(permissionCode));
//                            });
//                }
//            }
//            return grantedAuthorities;
//        }
//    }
//}
