package com.paung.config;

import com.paung.views.login.LoginView;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Set;

@Configuration
class SecurityConfiguration extends VaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    UserDetailsManager userDetailsManager() {
        var users = Set.of("paung", "udin", "ujang")
                .stream()
                .map(name -> User.withDefaultPasswordEncoder().username(name).password("ok").roles("USER").build())
                .toList();
        return new InMemoryUserDetailsManager(users);
    }

}


