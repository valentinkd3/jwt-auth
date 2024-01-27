package ru.kozhevnikov.jwtauth.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kozhevnikov.jwtauth.data.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private String username;

    private String password;

    private Set<GrantedAuthority> authorities;

    public CustomUserDetails(User user){
        username = user.getUsername();
        password = user.getPassword();
        authorities = Collections.singleton(user.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
