package com.study.ecommerce.model.user;

import com.study.ecommerce.entity.EcommerceUser;
import com.study.ecommerce.entity.Role;
import com.study.ecommerce.entity.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class UserDetailsCustom implements UserDetails {

    private Long id;

    private String email;

    private String username;

    private String password;

    private String status;

    private Set<Role> roles = new HashSet<>();

    private Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

    private EcommerceUser user;

    public UserDetailsCustom(EcommerceUser user, Set<GrantedAuthority> grantedAuthorities) {
        super();
        this.user = user;
        if (user != null) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.status = user.getStatus();
            if (!CollectionUtils.isEmpty(user.getUserRoles())) {
                this.roles = user.getUserRoles().stream().filter(userRole -> userRole.getRole() != null)
                        .map(UserRole::getRole).collect(Collectors.toSet());
            }
            this.grantedAuthorities = grantedAuthorities;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
