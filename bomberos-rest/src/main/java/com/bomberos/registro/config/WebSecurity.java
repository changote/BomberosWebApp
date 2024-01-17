package com.bomberos.registro.config;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.bomberos.registro.auth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Value("${front.mobile.pattern}")
    private String mobilePattern;

    @Value("${front.web.pattern}")
    private String webPattern;

    String[] resources = new String[]{
            "/assets/**", "/css/**", "/static/img/**", "/js/**"
    };

    @Autowired
    private CustomAuthenticationProvider authenticationManager;

    @Override
    protected AuthenticationManager authenticationManager() {
        return authenticationManager;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList(mobilePattern, webPattern));
        corsConfiguration.setAllowedHeaders(new ArrayList<String>(List.of("*")));
        corsConfiguration.setAllowedMethods(new ArrayList<String>(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH")));
        corsConfiguration.setAllowCredentials(true);

        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/login", "/**/swagger-ui/**", "/status", "/registro/nuevoregistro", "/unidad/allunidades", "/unidad/unidadcompletabyid").permitAll()
                .antMatchers("/registro/**", "/unidad/**", "/user/**", "/login/info").authenticated()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .cors().configurationSource(request -> corsConfiguration)
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");
    }
}