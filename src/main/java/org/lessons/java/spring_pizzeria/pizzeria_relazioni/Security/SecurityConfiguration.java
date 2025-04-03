package org.lessons.java.spring_pizzeria.pizzeria_relazioni.Security;

import org.lessons.java.spring_pizzeria.pizzeria_relazioni.Service.DatabaseUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    


    // ===============
    //  FILTER CHAIN
    // ===============

    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests()
                    .requestMatchers("pizze/create","pizze/edit/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST,"pizze/**").hasAuthority("ADMIN")
                    .requestMatchers("ingredients","ingredients/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST,"ingredients/**").hasAuthority("ADMIN")
                    .requestMatchers("discounts","discounts/**").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.POST,"discounts/**").hasAuthority("ADMIN")
                    .requestMatchers("/**").permitAll()
                    .and().formLogin()
                    .and().logout()
                    .and().exceptionHandling();

        return http.build();

    }


    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // userà x come servizio recupero username
        authProvider.setUserDetailsService(userDetailsService());


        // userà x come password encoder
        authProvider.setPasswordEncoder(passwordEncoder());


        return authProvider;
    }


    @Bean
    DatabaseUserDetailsService userDetailsService(){
        return new DatabaseUserDetailsService();
    }


    // Delegate to DB
    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
