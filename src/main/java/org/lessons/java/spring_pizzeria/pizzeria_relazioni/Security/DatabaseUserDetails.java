package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.Role;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class DatabaseUserDetails implements UserDetails{


    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;


    public DatabaseUserDetails(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<GrantedAuthority>();

        for (Role userRole : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }

    }

    public Integer getId() {
        return id;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



}
