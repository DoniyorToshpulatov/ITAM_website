package com.example.config;


import com.example.enums.ProfileRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    //@Autowired
    // private AuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers("/auth/registration").hasRole("ADMIN")
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/attach/**").permitAll()
                .requestMatchers("/profile/update").hasRole("ADMIN")
                .requestMatchers("/profile/update/attach").hasRole("ADMIN")
                .requestMatchers("/news/create").hasRole("ADMIN")
                .requestMatchers("/news/update").hasRole("ADMIN")
                .requestMatchers("/news/update/attach").hasRole("ADMIN")
                .requestMatchers("/news/delete").hasRole("ADMIN")
                .requestMatchers("/news/top3").permitAll()
                .requestMatchers("/news/more").permitAll()
                .requestMatchers("/news/admin").hasRole("ADMIN")
                .requestMatchers("/news/noDelete").hasRole("ADMIN")
                .requestMatchers("/deportment/create").hasRole("ADMIN")
                .requestMatchers("/deportment/update").hasRole("ADMIN")
                .requestMatchers("/deportment/update/attach").hasRole("ADMIN")
                .requestMatchers("/deportment/delete").hasRole("ADMIN")
                .requestMatchers("/deportment/noDelete").hasRole("ADMIN")
                .requestMatchers("/deportment/more").permitAll()
                .requestMatchers("/category/create").hasRole("ADMIN")
                .requestMatchers("/category/more").permitAll()
                .requestMatchers("/digest/more/categoryId").permitAll()
                .requestMatchers("/result/top").permitAll()
                .requestMatchers("/result/more").permitAll()
                .requestMatchers("/result/byId").permitAll()
                .requestMatchers("/partner/more").permitAll()
                .requestMatchers("/partner/byId").permitAll()
                .requestMatchers("/profile/deportment").permitAll()
                .requestMatchers("/profile/byId").permitAll()
                .anyRequest()
                .authenticated()
                .and().addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //   http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        return http.build();
    }


    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
            }
        };
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods(CorsConfiguration.ALL)
                        .allowedHeaders(CorsConfiguration.ALL)
                        .allowedOriginPatterns(CorsConfiguration.ALL)
                        .allowCredentials(true);
            }
        };
    }

}

