package com.authentication_web.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.authentication_web.Service.UserService;

@Configuration
@EnableWebSecurity
public class MyConfig {

	private UserService userService;
    @Autowired
    private CustomSuccessHandler customSuccessHandler;
    @Autowired
    private CustomFailureHandler customFailureHandler;

    @Autowired
    public MyConfig(UserService userService) {
        this.userService = userService;
    }

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/register", "/login").permitAll()
                        .requestMatchers("/block", "/unblock", "/delete").hasRole("USER")  // Ensure these match your controller mappings
                        .requestMatchers("/user_page").hasRole("USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler(customSuccessHandler)
                        .failureHandler(customFailureHandler))
                .logout(config -> config
                        .logoutSuccessUrl("/"))
                .userDetailsService(userService)
                .build();
    }


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
