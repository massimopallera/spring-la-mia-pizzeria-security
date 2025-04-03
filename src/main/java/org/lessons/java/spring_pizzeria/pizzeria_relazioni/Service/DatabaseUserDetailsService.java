package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Model.User;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Repository.UserRepository;
import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        Optional<User> userAttempt = userRepository.findByUsername(username);
    
        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("Username Not Found");
        }
    
        return new DatabaseUserDetails(userAttempt.get());
    }
    

}
