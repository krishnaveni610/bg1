package org.spo.ifs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {

        auth
                .inMemoryAuthentication()
                    .withUser("a").password("a").roles("ADMIN")
                        .and()
                    .withUser("user").password("a").roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll();

        http.formLogin().loginProcessingUrl("/login").failureUrl(
                "/login?error").defaultSuccessUrl("/");
        http.logout().logoutUrl("logout").logoutSuccessUrl("/login?loggedOut");
        http.httpBasic();

        /* You may want to disable CSRF protection, for instance if developing
         * an API? */
        //http.csrf().disable();

    }

}
