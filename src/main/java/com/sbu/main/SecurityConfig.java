package com.sbu.main;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Properties;

/**
 * Created by silda05 on 11/7/16.
 */
@CrossOrigin
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                //Protects all requests from unauthorized users
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
<<<<<<< HEAD
                .antMatchers("/create_user").permitAll()
                .antMatchers("/admin").permitAll()
=======
                .antMatchers("/**").permitAll()
>>>>>>> ab8078bb3008e3e0c033a35ce451901a91e295e3
                .anyRequest().authenticated()
                

                //Allows HTTP Basic auth
                .and().httpBasic()
<<<<<<< HEAD


=======
>>>>>>> ab8078bb3008e3e0c033a35ce451901a91e295e3
                .and().headers()
                .and().logout().disable()
                //
                .csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        final Properties users = new Properties();
<<<<<<< HEAD
        //add whatever other user you need
        users.put("admin", "pass,ROLE_ADMIN,enabled");
        users.put("employee","pass,ROLE_EMPLOYEE,enabled");
        users.put("customer","pass,ROLE_CUSTOMER,enabled");

=======
        users.put("user", "pass,ROLE_USER,enabled"); 
        //add whatever other user you need
        users.put("admin", "pass,ROLE_ADMIN,enabled");
        
        //used license pool address
>>>>>>> ab8078bb3008e3e0c033a35ce451901a91e295e3
        return new InMemoryUserDetailsManager(users);
    }

}
