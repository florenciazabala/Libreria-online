package com.egg.library.web.security;

import com.egg.library.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/*", "/images/*", "/find", "/login", "/signup", "/error", "/login-error","/save-signup").permitAll() // Recursos permitidos
                .antMatchers("/**").authenticated() // Recursos protegidos
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .loginProcessingUrl("/logincheck")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureUrl("/login?error=true")      //URL when authentication fails
                    .defaultSuccessUrl("/",true)
                    .permitAll()//Transition destination when authentication is successful
                .and()
                .logout()	//Logout settings
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       //Logout processing path
                    .logoutSuccessUrl("/login?logout=true")                                        //Path when logout is completed
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                .and()
                    .csrf()
                    .disable();
    }
}
