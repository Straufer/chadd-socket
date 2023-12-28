package com.paung.security;


import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import vaadin.views.login.LoginView;
import java.util.Set;

/*
 * hanya user khusus yang bisa chattingan
 * */
@Configuration
public class SecurityConfiguration extends VaadinWebSecurity { // jika ada dari vaadin kenapa harus spring security??

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        super.configure(httpSecurity);
        setLoginView(httpSecurity, LoginView.class);
    }

    @Bean
    UserDetailsManager userDetailsManager(){
        var users = Set.of("akmal", "ujang", "paung")
                .stream()
                .map(name -> User.withDefaultPasswordEncoder().username(name).password("ok").roles("USER").build()) // => they said "its considered unsafe for production and is only intended for sample applications" wtffffff im not deploy it to fkin production
                .toList();
        return new InMemoryUserDetailsManager((users));
    }

}
