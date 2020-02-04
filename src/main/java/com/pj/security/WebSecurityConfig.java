package com.pj.security;

import com.pj.security.jwt.JwtAuthEntryPoint;
import com.pj.security.jwt.JwtAuthTokenFilter;
import com.pj.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/api/auth/signin").permitAll()
                .antMatchers("/api/getRoles").permitAll()
                .antMatchers("/image/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/signup").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/api/syllabus",
                        "/api/objective",
                        "/api/skill",
                        "/api/activity").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_PM')")
                .antMatchers(HttpMethod.POST,
                        "/api/syllabus/create",
                        "/api/objective/create",
                        "/api/skill/create",
                        "/api/activity/create").access("hasRole('ROLE_PM')")
                .antMatchers(HttpMethod.PUT,
                        "/api/syllabus/update",
                        "/api/objective/update",
                        "/api/skill/update",
                        "/api/activity/update").access("hasRole('ROLE_PM')")
                .antMatchers(HttpMethod.DELETE,
                        "/api/syllabus/{id}",
                        "/api/objective/{id}",
                        "/api/skill/{id}",
                        "/api/activity/{id}").access("hasRole('ROLE_PM')")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.cors();
    }
}
